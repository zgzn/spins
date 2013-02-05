// Copyright 2010, University of Twente, Formal Methods and Tools group
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package spins.search;

/**
 * A SearchableStack is any object that holds many states in such a way that it can be read through
 * an iterator. Also it should be possible to check if a state is already contained in this stack
 * and a simple way to get the top of the stack.
 * 
 * @author Marc de Jonge
 */
public interface SearchableStack {
	/**
	 * Checks if the given state is already stored on the stack.
	 * 
	 * @param state
	 *            The state that we have to look for.
	 * @return True when the state is found, or false if it is not on the stack
	 */
	public boolean containsState(byte[] state, int identifier);
}