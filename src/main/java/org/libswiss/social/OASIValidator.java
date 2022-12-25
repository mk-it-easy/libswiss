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

import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;

import java.util.regex.Pattern;

/**
 * <p>Perform validation for swiss OASI (Old-Age and Survivors Insurance) number, or AHV-Nr.</p>
 *
 * <p>Based on the description of OASI number found at
 * <a href="https://ethz.ch/applications/teaching/en/applications/mystudies/welcome/ahvnr.html">ETH Zurich</a>.</p>
 *
 * <p>This implementation is not guaranteed to catch all errors in OASI number.</p>
 *
 * @version $Revision$
 * @since Swiss 1.0
 */
public class OASIValidator {

    private static final String SERPARATOR = "\\.";
    private static final String EMPTY_STRING = "";
    private static final String COUNTRY_CODE = "(756)";
    private static final String FOUR_DIGIT = "(\\d{4})";
    private static final String TWO_DIGIT = "(\\d{2})";

    private static final Pattern OASI_PATTERN = Pattern.compile(COUNTRY_CODE + SERPARATOR + FOUR_DIGIT
            + SERPARATOR + FOUR_DIGIT + SERPARATOR + TWO_DIGIT);
    private static final OASIValidator OASI_VALIDATOR = new OASIValidator();

    private OASIValidator() {}

    /**
     * <p>Returns the singleton instance of this validator.</p>
     *
     * @return singleton instance of this validator.
     */
    public static OASIValidator getInstance() {
        return OASI_VALIDATOR;
    }

    /**
     * <p>Checks if a field has a valid OASI number.</p>
     *
     * @param oasi The value validation is performed on. A <code>null</code>
     *                     value is considered invalid.
     *@return if the OASI number is valid.
     */
    public boolean isValid(String oasi) {
        if (oasi == null)
            return false;

        EAN13CheckDigit ean13CheckDigit = new EAN13CheckDigit();

        return OASI_PATTERN.matcher(oasi).matches()
                && ean13CheckDigit.isValid(oasi.replaceAll(SERPARATOR, EMPTY_STRING));
    }
}
