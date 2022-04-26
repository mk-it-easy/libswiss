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

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * <p>Perform car license plate validation for swiss registered cars.</p>
 *
 * <p>Based on the description of license plates found at
 * <a href="https://de.wikipedia.org/wiki/Kontrollschild_%28Schweiz%29">german wikipedia</a>.</p>
 *
 * <p>This implementation is not guaranteed to catch all errors in a license plate.</p>
 *
 * @version $Revision$
 * @since Swiss 1.0
 */
public class LicensePlateValidator implements Serializable {

    private static final String SEPARATORS = "(-)?";
    private static final String CANTON = "(A[GIR]|B[ELS]|FR|G[ELR]|JU|LU|N[EW]|OW|S[GHOZ]|T[GI]|UR|V[DS]|Z[GH])";
    private static final String SERIAL = "[1-9]\\d{0,5}";
    private static final String SPECIAL_PURPOSE = "([UZ])?";

    private static final String DIPLOMATIC = "(C[CD]|AT)";
    private static final String DIPLOMATIC_SERIAL = "[1-9]\\d*";
    private static final String DIPLOMATIC_SEPARATOR = "([.*-])";
    private static final String DIPLOMATIC_CODE_UN = "(0([1-9]|[1,2]\\d|3[0-5]))";
    private static final String DIPLOMATIC_CODE_INTERNATIONAL_ORGANIZATION = "2(0[1-9]|1[0-7])";
    private static final String DIPLOMATIC_CODE_INTERNATIONAL_ORGANIZATION_COUNTRIES = "[3,5]";
    private static final String DIPLOMATIC_CODE_COUNTRIES = "[1-9]|[1-9]{2}|1[0-7]\\d|18[0,1]";

    private static final String MILITARY = "M";

    private static final String REGULAR_LICENSE_PLATE = CANTON + SEPARATORS + SERIAL + SPECIAL_PURPOSE;
    private static final String MILITARY_LICENSE_PLATE = MILITARY + SERIAL;
    private static final String DIPLOMATIC_LICENSE_PLATE = DIPLOMATIC + CANTON + DIPLOMATIC_SERIAL + DIPLOMATIC_SEPARATOR
            + "(" + DIPLOMATIC_CODE_UN
            + "|" + DIPLOMATIC_CODE_INTERNATIONAL_ORGANIZATION
            + "|" + DIPLOMATIC_CODE_INTERNATIONAL_ORGANIZATION_COUNTRIES + DIPLOMATIC_CODE_COUNTRIES + ")";

    private static final Pattern REGULAR_LICENSE_PLATE_PATTERN = Pattern.compile(REGULAR_LICENSE_PLATE);
    private static final Pattern DIPLOMATIC_LICENSE_PLATE_PATTERN = Pattern.compile(DIPLOMATIC_LICENSE_PLATE);
    private static final Pattern MILITARY_LICENSE_PLATE_PATTERN = Pattern.compile(MILITARY_LICENSE_PLATE);

    /**
     * <p>Singleton instance of this class.</p>
     */
    private static final LicensePlateValidator LICENSE_PLATE_VALIDATOR = new LicensePlateValidator();

    private LicensePlateValidator() {
    }

    /**
     * <p>Returns the singleton instance of this validator.</p>
     *
     * @return singleton instance of this validator.
     */
    public static LicensePlateValidator getInstance() {
        return LICENSE_PLATE_VALIDATOR;
    }

    /**
     * <p>Checks if a field has a valid license plate.</p>
     *
     * @param licensePlate The  value validation is performed on. A <code>null</code>
     *                     value is considered invalid.
     * @return if the license plate is a valid swiss license plate
     */
    public boolean isValid(String licensePlate) {
        if (licensePlate == null) {
            return false;
        }
        licensePlate = licensePlate.toUpperCase();
        licensePlate = licensePlate.replaceAll("\\s", "");

        if (REGULAR_LICENSE_PLATE_PATTERN.matcher(licensePlate).matches()) {
            return true;
        }

        if ( DIPLOMATIC_LICENSE_PLATE_PATTERN.matcher(licensePlate).matches()) {
            return true;
        }

        return MILITARY_LICENSE_PLATE_PATTERN.matcher(licensePlate).matches();
    }

}
