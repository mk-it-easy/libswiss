/*
 * Copyright 2022 Martin Kaiser
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
package org.libswiss.aircraft;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AircraftRegistrationValidatorTest {

    @Test
    void isValid() {
        AircraftRegistrationValidator aircraftRegistrationValidator = AircraftRegistrationValidator.getInstance();

        assertTrue(aircraftRegistrationValidator.isValid("HB-1"));
        assertTrue(aircraftRegistrationValidator.isValid("HB-23"));
        assertTrue(aircraftRegistrationValidator.isValid("HB-345"));
        assertTrue(aircraftRegistrationValidator.isValid("HB-4567"));
        assertTrue(aircraftRegistrationValidator.isValid("HB-ABC"));
        assertTrue(aircraftRegistrationValidator.isValid("HB-XKD"));
        assertTrue(aircraftRegistrationValidator.isValid("X-HB-XKD"));
        assertTrue(aircraftRegistrationValidator.isValid("J-2003"));
        assertTrue(aircraftRegistrationValidator.isValid("R-2103"));
        assertTrue(aircraftRegistrationValidator.isValid("V-603"));
        assertTrue(aircraftRegistrationValidator.isValid("Z-78"));

        assertFalse(aircraftRegistrationValidator.isValid("HB-56789"));
        assertFalse(aircraftRegistrationValidator.isValid("HB-DFRE"));
        assertFalse(aircraftRegistrationValidator.isValid("HB-DF"));
        assertFalse(aircraftRegistrationValidator.isValid("HB-U"));
        assertFalse(aircraftRegistrationValidator.isValid("J-20004"));
        assertFalse(aircraftRegistrationValidator.isValid("Z-7"));
        assertFalse(aircraftRegistrationValidator.isValid("Y-7"));
    }
}