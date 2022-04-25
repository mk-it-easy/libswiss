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
package org.libswiss.vehicle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LicensePlateValidatorTest {

    @Test
    void isValid() {
        LicensePlateValidator licensePlateValidator = LicensePlateValidator.getInstance();

        assertTrue(licensePlateValidator.isValid("BS1"));
        assertTrue(licensePlateValidator.isValid("TI 654934"));
        assertTrue(licensePlateValidator.isValid("TI654 934"));
        assertTrue(licensePlateValidator.isValid("AI 654 934"));
        assertTrue(licensePlateValidator.isValid("AR - 494"));
        assertTrue(licensePlateValidator.isValid("ZH-5435"));
        assertTrue(licensePlateValidator.isValid("ZH-5435 Z"));
        assertTrue(licensePlateValidator.isValid("ZH-5435 U"));

        assertFalse(licensePlateValidator.isValid(""));
        assertFalse(licensePlateValidator.isValid(null));
        assertFalse(licensePlateValidator.isValid("HY 123"));
        assertFalse(licensePlateValidator.isValid("AG . 123 456"));

        assertTrue(licensePlateValidator.isValid("CDBE1*15"));
        assertTrue(licensePlateValidator.isValid("CCZH8-38"));
        assertTrue(licensePlateValidator.isValid("CD VD 351 . 03"));
        assertTrue(licensePlateValidator.isValid("CD GE 29.201"));
        assertTrue(licensePlateValidator.isValid("AT BE 53 - 39"));

        assertTrue(licensePlateValidator.isValid("M 123 456"));
    }
}