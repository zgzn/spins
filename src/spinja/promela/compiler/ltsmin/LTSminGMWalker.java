package spinja.promela.compiler.ltsmin;

import static spinja.promela.compiler.ltsmin.LTSminTreeWalker.channelTop;
import static spinja.promela.compiler.ltsmin.LTSminTreeWalker.compare;
import static spinja.promela.compiler.ltsmin.LTSminTreeWalker.constant;

import java.util.ArrayList;
import java.util.List;

import spinja.promela.compiler.expression.BooleanExpression;
import spinja.promela.compiler.expression.ChannelLengthExpression;
import spinja.promela.compiler.expression.ChannelOperation;
import spinja.promela.compiler.expression.ChannelReadExpression;
import spinja.promela.compiler.expression.CompareExpression;
import spinja.promela.compiler.expression.Expression;
import spinja.promela.compiler.expression.Identifier;
import spinja.promela.compiler.ltsmin.instr.ChannelSizeExpression;
import spinja.promela.compiler.ltsmin.instr.ChannelTopExpression;
import spinja.promela.compiler.ltsmin.instr.DepMatrix;
import spinja.promela.compiler.ltsmin.instr.GuardInfo;
import spinja.promela.compiler.parser.ParseException;
import spinja.promela.compiler.parser.PromelaConstants;
import spinja.promela.compiler.parser.PromelaTokenManager;
import spinja.promela.compiler.variable.ChannelType;
import spinja.promela.compiler.variable.Variable;
import spinja.promela.compiler.variable.VariableType;

/**
 *
 * @author FIB
 */
public class LTSminGMWalker {

	static public class Params {
		public final LTSminModel model;
		public final GuardInfo guardMatrix;
		public int trans;
		public int guards;

		public Params(LTSminModel model, GuardInfo guardMatrix) {
			this.model = model;
			this.guardMatrix = guardMatrix;
			this.trans = 0;
			this.guards = 0;
		}
	}

	static void walkModel(LTSminModel model) {
		if(model.getGuardInfo()==null)
			model.setGuardInfo(new GuardInfo(model.getTransitions().size()));
		GuardInfo guardInfo = model.getGuardInfo();
		Params params = new Params(model, guardInfo);
		// extact guards bases
		walkTransitions(params);

		// generate guard DM
		generateGuardMatrix(model, guardInfo);
		
		// generate Maybe Coenabled matrix
		generateCoenMatrix (guardInfo);
		
		// generate NES matrix
		generateNESMatrix (guardInfo);

		// generate NDS matrix
		generateNDSMatrix (guardInfo);
	}

	private static void generateNDSMatrix(GuardInfo guardInfo) {
		
	}

	private static void generateNESMatrix(GuardInfo guardInfo) {
		
	}

	private static void generateGuardMatrix(LTSminModel model,
			GuardInfo guardInfo) {
		DepMatrix dm = new DepMatrix(guardInfo.size(), model.sv.size());
		guardInfo.setDepMatrix(dm);
		for (int i = 0; i < guardInfo.size(); i++) {
			LTSminDMWalker.walkOneGuard(model, dm, guardInfo.get(i), i);
		}
	}

	private static void generateCoenMatrix(GuardInfo gm) {
		DepMatrix co = new DepMatrix(gm.size(), gm.size());
		gm.setCoMatrix(co);
		int neverCoEnabled = 0;
		for (int i = 0; i < gm.size(); i++) {
			// same guard is always coenabled:
			co.incRead(i, i);
			for (int j = i+1; j < gm.size(); j++) {
				if (mayBeCoenabled(gm.get(i),gm.get(j))) {
					co.incRead(i, j);
					co.incRead(j, i);
				} else {
					neverCoEnabled++;
				}
			}
		}
		
		System.out.println("Found "+ neverCoEnabled +"/"+ gm.size()*gm.size()/2 +" guards that can never be enabled at the same time!");
	}
	
	private static boolean mayBeCoenabled(LTSminGuard g1, LTSminGuard g2) {
		return mayBeCoenabledStrong (g1.expr, g2.expr);
	}

	/**
	 * Determine MCE over disjuctions: MCE holds for ex1 and ex2 iff 
	 * it holds over for all d1,d2 in disjuctions(ex1) X disjunctions(ex2)
	 */
	private static boolean mayBeCoenabledStrong(Expression ex1, Expression ex2) {
        List<Expression> ga_ex = new ArrayList<Expression>();
        List<Expression> gb_ex = new ArrayList<Expression>();
        extract_disjunctions (ga_ex, ex1);
        extract_disjunctions (gb_ex, ex2);
        for(Expression a : ga_ex) {
            for(Expression b : gb_ex) {
                if (mayBeCoenabled(a, b)) {
                	return true;
                }
            }
        }
        return false;
	}

	/**
	 * Extracts all disjuctions untill conjunctions or arithmicExpr are encountered
	 */
	private static void extract_disjunctions (List<Expression> ds, Expression e) {
		if(e instanceof BooleanExpression) {
			BooleanExpression ce = (BooleanExpression)e;
			if (ce.getToken().kind == PromelaTokenManager.BOR ||
				ce.getToken().kind == PromelaTokenManager.LOR) {
				extract_disjunctions (ds, ce.getExpr1());
				extract_disjunctions (ds, ce.getExpr2());
			} else {
				ds.add(e);
			}
		} else {
			ds.add(e);
		}
	}

	static class SimplePredicate {
		public SimplePredicate(int kind, String var, int c) {
			comparison = kind;
			this.var = var;
			this.constant = c;
		}
		public int comparison;
		public String var;
		public int constant;
	}

	/**
	 * Determine MCE over conjunctions: MCE holds for ex1 and ex2 iff 
	 * all sp1,sp2 in simplePreds(ex1) X simplePreds(ex2) do no conflict
	 */
	private static boolean mayBeCoenabled(Expression ex1, Expression ex2) {
        List<SimplePredicate> ga_sp = new ArrayList<SimplePredicate>();
        List<SimplePredicate> gb_sp = new ArrayList<SimplePredicate>();
        extract_predicates(ga_sp, ex1);
        extract_predicates(gb_sp, ex2);
        for(SimplePredicate a : ga_sp) {
            for(SimplePredicate b : gb_sp) {
                if (is_conflict_predicate(a, b)) {
                	return false;
                }
            }
        }
        return true;
	}

	/**
	 * Collects all simple predicates in an expression e.
	 * SimplePred ::= cvarref <comparison> constant | constant <comparison> cvarref
	 * where cvarref is a reference to a singular (channel) variable or a
	 * constant index in array variable.
	 */
	private static void extract_predicates(List<SimplePredicate> sp, Expression e) {
		int c;
		String var;
    	if(e instanceof CompareExpression) {
    		CompareExpression ce1 = (CompareExpression)e;
    		try {
    			var = getConstantVar(ce1.getExpr1());
    			c = ce1.getExpr2().getConstantValue();
        		sp.add(new SimplePredicate(e.getToken().kind, var, c));
    		} catch (ParseException pe) {
        		try {
        			var = getConstantVar(ce1.getExpr2());
        			c = ce1.getExpr1().getConstantValue();
            		sp.add(new SimplePredicate(e.getToken().kind, var, c));
        		} catch (ParseException pe2) {}
    		}
		} else if(e instanceof ChannelTopExpression) {
			ChannelTopExpression cte = (ChannelTopExpression)e;
			Identifier id = cte.getChannelReadAction().getIdentifier();
			int i = 0;
			for (Expression read : cte.getChannelReadAction().getExprs()) {
				try { // this is a conjunction of matchings
					i += 1;
	    			var = getConstantVar(id) +"["+ i +"]";
	    			c = read.getConstantValue();
            		sp.add(new SimplePredicate(e.getToken().kind, var, c));
	    		} catch (ParseException pe2) {}
			}
		} else if(e instanceof ChannelReadExpression) {
			ChannelReadExpression cre = (ChannelReadExpression)e;
			Identifier id = cre.getIdentifier();
			extract_predicates(sp, compare(PromelaConstants.GT,
									new ChannelSizeExpression(id), constant(0)));
			List<Expression> exprs = cre.getExprs();
			for (int i = 0; i < exprs.size(); i++) {
				try { // this is a conjunction of matchings
					final Expression expr = exprs.get(i);
					extract_predicates(sp, compare(PromelaConstants.EQ,
							channelTop(id, i), constant(expr.getConstantValue())));
		    	} catch (ParseException pe2) {}
			}
    	} else if(e instanceof ChannelOperation) {
			ChannelOperation co = (ChannelOperation)e;
			String name = co.getToken().image;
			Identifier id = (Identifier)co.getExpression();
			VariableType type = id.getVariable().getType();
			int buffer = ((ChannelType)type).getBufferSize();
			Expression left = new ChannelSizeExpression(id);
			Expression right = null;
			int op = -1;
			if (name.equals("empty")) {
				op = PromelaConstants.EQ;
				right = constant (0);
			} else if (name.equals("nempty")) {
				op = PromelaConstants.NEQ;
				right = constant (0);
			} else if (name.equals("full")) {
				op = PromelaConstants.EQ;
				right = constant (buffer);
			} else if (name.equals("nfull")) {
				op = PromelaConstants.NEQ;
				right = constant (buffer);
			}
			extract_predicates(sp, compare(op, left, right));
		} else if(e instanceof BooleanExpression) {
    		BooleanExpression ce = (BooleanExpression)e;
    		if (ce.getToken().kind == PromelaTokenManager.BAND ||
    			ce.getToken().kind == PromelaTokenManager.LAND) {
    			extract_predicates (sp, ce.getExpr1());
    			extract_predicates (sp, ce.getExpr2());
    		}
		}
	}

	/**
	 * Tries to parse an expression as a reference to a singular (channel)
	 * variable or a constant index in array variable (a cvarref).
	 */
	private static String getConstantVar(Expression e) throws ParseException {
		if (e instanceof Identifier) {
			Identifier id = (Identifier)e;
			Variable var = id.getVariable();
			if (var.getArraySize() > 1)
				return var.getRealName()+ "["+ id.getArrayExpr().getConstantValue() +"]"; // may throw exception
			/*if (var instanceof ChannelVariable) {
				if (((ChannelVariable)var).getType().getBufferSize() > 1)
					throw new ParseException();
			}*/ // only needed for random channel reads; not for topExpr + readExpr 
			return var.getRealName();
		} else if (e instanceof ChannelSizeExpression)  {
			Identifier id = ((ChannelSizeExpression)e).getIdentifier();
			Variable var = id.getVariable();
			if (var.getArraySize() > 1)
				return var.getRealName()+ "["+ id.getArrayExpr().getConstantValue() +"].filled"; // may throw exception
			return var.getRealName() +".filled";
		} else if (e instanceof ChannelLengthExpression)  {
			ChannelLengthExpression cle = (ChannelLengthExpression)e;
			Identifier id = (Identifier)cle.getExpression();
			return getConstantVar(new ChannelSizeExpression(id));
		}
		throw new ParseException();
	}

	private static boolean is_conflict_predicate(SimplePredicate p1, SimplePredicate p2) {
	    // assume no conflict
	    boolean no_conflict = true;
	    // conflict only possible on same variable
	    if (p1.var.equals(p2.var)) {
	        switch(p1.comparison) {
	            case PromelaConstants.LT:
	                // no conflict if one of these cases
	                no_conflict =
	                (p2.constant < p1.constant - 1) ||
	                (p2.constant == p1.constant - 1 && p2.comparison != PromelaConstants.GT) ||
	                (p2.comparison == PromelaConstants.LT || p2.comparison == PromelaConstants.LTE || p2.comparison == PromelaConstants.NEQ);
	                break;
	
	            case PromelaConstants.LTE:
	                // no conflict if one of these cases
	                no_conflict =
	                (p2.constant < p1.constant) ||
	                (p2.constant == p1.constant && p2.comparison != PromelaConstants.GT) ||
	                (p2.comparison == PromelaConstants.LT || p2.comparison == PromelaConstants.LTE || p2.comparison == PromelaConstants.NEQ);
	                break;
	
	            case PromelaConstants.EQ:
	                // no conflict if one of these cases
	                no_conflict =
	                (p2.constant == p1.constant && (p2.comparison == PromelaConstants.EQ || p2.comparison == PromelaConstants.LTE || p2.comparison == PromelaConstants.GTE)) ||
	                (p2.constant != p1.constant && p2.comparison == PromelaConstants.NEQ) ||
	                (p2.constant < p1.constant && p2.comparison == PromelaConstants.GT || p2.comparison == PromelaConstants.GTE) ||
	                (p2.constant > p1.constant && (p2.comparison == PromelaConstants.LT || p2.comparison == PromelaConstants.LTE));
	                break;
	
	            case PromelaConstants.NEQ:
	                // no conflict if one of these cases
	                no_conflict =
	                (p2.constant != p1.constant) ||
	                (p2.constant == p1.constant && p2.comparison != PromelaConstants.EQ);
	                break;
	
	            case PromelaConstants.GT:
	                // no conflict if one of these cases
	                no_conflict =
	                (p2.constant > p1.constant + 1) ||
	                (p2.constant == p1.constant + 1 && p2.comparison != PromelaConstants.LT) ||
	                (p2.comparison == PromelaConstants.GT || p2.comparison == PromelaConstants.GTE || p2.comparison == PromelaConstants.NEQ);
	                break;
	
	            case PromelaConstants.GTE:
	                // no conflict if one of these cases
	                no_conflict =
	                (p2.constant > p1.constant) ||
	                (p2.constant == p1.constant && p2.comparison != PromelaConstants.LT) ||
	                (p2.comparison == PromelaConstants.GT || p2.comparison == PromelaConstants.GTE || p2.comparison == PromelaConstants.NEQ);
	                break;
	        }
	    }
	    return !no_conflict;
	}

	static void walkTransitions(Params params) {
		for(LTSminTransitionBase t : params.model.getTransitions()) {
			walkTransition(params,t);
			params.trans++;
		}
	}

	static void walkTransition(	Params params, LTSminTransitionBase transition) {
		if(transition instanceof LTSminTransition) {
			LTSminTransition t = (LTSminTransition)transition;
			List<LTSminGuardBase> guards = t.getGuards();
			for(LTSminGuardBase g: guards)
				walkGuard(params, g);
		} else if (transition instanceof LTSminTransitionCombo) {
			LTSminTransitionCombo t = (LTSminTransitionCombo)transition;
			for(LTSminTransitionBase tb: t.transitions)
				walkTransition(params,tb);
		} else {
			throw new AssertionError("UNSUPPORTED: " + transition.getClass().getSimpleName());
		}
	}

	static void walkGuard(Params params, LTSminGuardBase guard) {
		if(guard instanceof LTSminLocalGuard) { //Nothing
		} else if(guard instanceof LTSminGuard) {
			LTSminGuard g = (LTSminGuard)guard;
			params.guardMatrix.addGuard(params.trans, g);
		} else if(guard instanceof LTSminGuardNand) {
			LTSminGuardNand g = (LTSminGuardNand)guard;
			for(LTSminGuardBase gb : g.guards)
				walkGuard(params, gb);
		} else if(guard instanceof LTSminGuardAnd) {
			LTSminGuardAnd g = (LTSminGuardAnd)guard;
			for(LTSminGuardBase gb : g.guards)
				walkGuard(params,gb);
		} else if(guard instanceof LTSminGuardOr) {
			LTSminGuardOr g = (LTSminGuardOr)guard;
			for(LTSminGuardBase gb : g.guards)
				walkGuard(params,gb);
		} else {
			throw new AssertionError("UNSUPPORTED: " + guard.getClass().getSimpleName());
		}
	}
}
