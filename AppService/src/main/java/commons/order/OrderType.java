/*
 * Copyright 2019 Maksim Zheravin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package commons.order;

import lombok.Getter;

@Getter
public enum OrderType {
    LIMIT(0); // Immediate or Cancel - equivalent to strict-risk market order

    private byte type;

    OrderType(int type) {
        this.type = (byte) type;
    }

    public static OrderType of(byte type) {
        switch (type) {
            case 0:
                return LIMIT;
            default:
                throw new IllegalArgumentException("unknown OrderType:" + type);
        }
    }

}
