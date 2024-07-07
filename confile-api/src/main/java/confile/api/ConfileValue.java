/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package confile.api;

/**
 * TODO-Kenown Convalue
 *
 * @author Kenown
 * @since 1.0.0
 */
public interface ConfileValue extends ConfileMergeable {

    ConfileOrigin origin();

    ConfileValueType type();

    Object unwrapped();

    String render();

    String render(ConfileRenderOptions options);

    @Override
    ConfileValue merge(ConfileMergeable other);

    @Override
    ConfileValue merge(ConfileMergeable other, ConfileMergeMode mode);

    /**
     * Create a {@link Confile},
     * put the current ConfileValue into the given path of the Confile,
     * and then return the Confile.
     *
     * @param path path to store this ConfileValue at.
     * @return a {@link Confile} instance containing this ConfileValue at the given path.
     * @see #atKey(String)
     */
    Confile atPath(String path);

    /**
     * Create a {@link Confile},
     * put the current ConfileValue into the given key of the Confile,
     * and then return the Confile.
     *
     * @param key key to store this ConfileValue at.
     * @return a {@link Confile} instance containing this ConfileValue at the given key.
     * @see #atPath(String)
     */
    Confile atKey(String key);

}
