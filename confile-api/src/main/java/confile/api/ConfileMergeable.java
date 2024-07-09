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
 * Marker for types whose instances can be merged, that is {@link Confile} and {@link ConfileValue}.
 * Instances of {@code Confile} and {@code ConfileValue} can be merged
 * using the {@link #merge(ConfileMergeable)} and {@link #merge(ConfileMergeable, ConfileMergeMode)} methods.
 *
 * <p>
 * This associative operation may be used to combine configurations
 * from multiple sources (such as multiple configuration files).
 *
 * <p>
 * Merging typically occurs when either the same object is created twice in the same file,
 * or two configuration files are both loaded. For example:
 *
 * <pre>
 *     foo = { a : 1 }
 *     foo = { b : 2 }
 * </pre>
 *
 * Here, the two objects are merged as if you had written:
 *
 * <pre>
 *     foo = { a : 1, b : 2 }
 * </pre>
 *
 * <p>
 * When merging, if there are identical keys in two instances,
 * the key value in the current instance will be retained by default.
 * You kan use the {@link ConfileMergeMode} to change this behavior.
 *
 * @author Kenown
 * @since 1.0.0
 */
public interface ConfileMergeable {

    /**
     * Returns the value computed by merging this value with another,
     * with this value taking precedence.
     *
     * <p>
     * The returned value is a new instance or the modified current instance depending on the specific implementation.
     *
     * @param other another object.
     * @return the merged object.
     */
    ConfileMergeable merge(ConfileMergeable other);

    /**
     * Returns the value computed by merging this value with another,
     * with the specified mode determining how conflicts are resolved.
     *
     * <p>
     * The returned value is a new instance or the modified current instance depending on the specific implementation.
     *
     * @param other another object.
     * @param mode the merge mode.
     * @return the merged object.
     */
    ConfileMergeable merge(ConfileMergeable other, ConfileMergeMode mode);

}
