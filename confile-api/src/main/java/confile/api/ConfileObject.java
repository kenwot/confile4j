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

import com.typesafe.config.Config;

import java.util.Map;

/**
 * Subtype of {@link ConfileValue} representing an object (AKA dictionary or map) value,
 * as in JSON's curly brace <code>{ "a" : 42 }</code> syntax.
 *
 * <p>
 * An object may also be viewed as a {@link Config} by calling {@link #toConfile()}.
 *
 * @author Kenown
 * @since 1.0.0
 */
public interface ConfileObject extends ConfileValue, Map<String, ConfileValue> {

    /**
     * Converts this object to a {@link Confile} instance.
     *
     * @return a {@link Confile} with this object as its root.
     */
    Confile toConfile();

    /**
     * Recursively unwraps the object,
     * returning a map from String to whatever plain Java values are unwrapped from the object's values.
     *
     * @return a {@link java.util.Map} containing plain Java objects.
     */
    @Override
    Map<String, Object> unwrapped();

    @Override
    ConfileObject merge(ConfileMergeable other);

    @Override
    ConfileObject merge(ConfileMergeable other, ConfileMergeMode mode);

    /**
     * Gets a {@link ConfileValue} at the given key, or returns null if there is no value.
     * The returned {@link ConfileValue} may have a {@link ConfileValueType#NULL} value type or any other type,
     * and the passed-in key must be a key in this object (rather than a path expression).
     *
     * @param key key to look up.
     * @return the value at the key or null if none.
     */
    @Override
    ConfileValue get(Object key);

    /**
     * Returns a {@link ConfileObject} based on this one, but with only the given key (and its children).
     *
     * <p>
     * The returned value is a new instance or the modified current instance depending on the specific implementation.
     *
     * @param key key to keep.
     * @return an instance minus all keys except the one specified.
     */
    ConfileObject onlyKey(String key);

    /**
     * Returns a {@link ConfileObject} based on this one, but without the give key.
     *
     * <p>
     * The returned value is a new instance or the modified current instance depending on the specific implementation.
     *
     * @param key key to remove.
     * @return an instance without the given key.
     */
    ConfileObject withoutKey(String key);

    /**
     * Returns a {@link ConfileObject} based on this one, but with the give key set to the given value.
     * If the key already has a value, that value is replaced.
     * To remove a value, use {@link #withoutKey(String)}.
     *
     * <p>
     * The returned value is a new instance or the modified current instance depending on the specific implementation.
     *
     * @param key key to add.
     * @param value value at the new key.
     * @return an instance with the new map entry.
     */
    ConfileObject withValue(String key, ConfileValue value);

    @Override
    ConfileObject withOrigin(ConfileOrigin origin);

}
