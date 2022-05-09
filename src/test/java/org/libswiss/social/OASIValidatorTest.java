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
package org.libswiss.social;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OASIValidatorTest {

    @Test
    void isValid() {
        OASIValidator oasiValidator = OASIValidator.getInstance();

        assertTrue(oasiValidator.isValid("756.3521.1980.05"));
        assertTrue(oasiValidator.isValid("756.4125.1340.00"));

        assertFalse(oasiValidator.isValid(null));
        assertFalse(oasiValidator.isValid("756.4225.1340.00"));
        assertFalse(oasiValidator.isValid("757.4125.1340.09"));
        assertFalse(oasiValidator.isValid("756.4125.134.009"));
        assertFalse(oasiValidator.isValid("756.412.51340.09"));
        assertFalse(oasiValidator.isValid("756.412.5.1340.09"));
    }
}