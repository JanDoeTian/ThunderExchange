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
public enum OrderDirection {

    BUY(0),
    SELL(1),

    PLUS_BALANCE(2),
    MINUS_BALANCE(3),

    OTHER(-1);

    private byte direction;

    OrderDirection(int direction) {
        this.direction = (byte) direction;
    }

    public static OrderDirection of(byte direction) {
        switch (direction) {
            case 0:
                return BUY;
            case 1:
                return SELL;
            case 2:
                return PLUS_BALANCE;
            case 3:
                return MINUS_BALANCE;
            case -1:
                return OTHER;
            default:
                throw new IllegalArgumentException("unknown OrderDirection:" + direction);
        }
    }

}
