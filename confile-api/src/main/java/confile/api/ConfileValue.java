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
 * A value following the <a href="https://json.org">JSON</a> type schema.
 *
 * @author Kenown
 * @since 1.0.0
 */
public interface ConfileValue extends ConfileMergeable {

    /**
     * The origin of the value (file, line number, etc.),
     * for debugging and error messages.
     *
     * @return where the value came from.
     */
    ConfileOrigin origin();

    /**
     * The {@link ConfileValueType} of the value; matches the JSON type schema.
     *
     * @return value's type.
     */
    ConfileValueType type();

    /**
     * Returns the value as a plain Java boxed value,
     * that is, {@code String}, {@code Number}, {@code Boolean},
     * {@code Map<String,Object>}, {@code List<Object>}, or {@code null},
     * matching the {@link #type()} of this {@link ConfileValue}.
     *
     * <p>
     * If the value is a {@link ConfileObject} or {@link ConfileList}, it is recursively unwrapped.
     *
     * @return a plain Java value corresponding to this ConfileValue.
     */
    Object unwrapped();

    /**
     * Renders the confile value to a string, using the {@link ConfileRenderOptions#defaults()} options.
     *
     * @return the rendered value
     */
    String render();

    /**
     * Renders the confile value to a string, using the provided options.
     *
     * @param options the rendering options
     * @return the rendered value
     */
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

    /**
     * Returns a {@code ConfileValue} based on this one, but with the given
     * origin. This is useful when you are parsing a new format of file or setting
     * comments for a single ConfigValue.
     *
     * <p>
     * The returned value is a new instance or the modified current instance depending on the specific implementation.
     *
     * @param origin the origin set on the returned value
     * @return ConfileValue with the given origin
     */
    ConfileValue withOrigin(ConfileOrigin origin);

}
