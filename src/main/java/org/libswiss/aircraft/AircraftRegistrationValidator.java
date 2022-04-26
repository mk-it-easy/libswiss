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

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * <p>Perform aircraft registration validation for swiss registered aircraft.</p>
 *
 * <p>Based on the description of license plates found at
 * <a href="https://de.wikipedia.org/wiki/Luftfahrzeugkennzeichen">german wikipedia</a>.</p>
 *
 * <p>This implementation is not guaranteed to catch all errors in a aircraft registration.</p>
 *
 * @version $Revision$
 * @since Swiss 1.0
 */
public class AircraftRegistrationValidator implements Serializable {

    private static final String SWISS_PREFIX = "(HB-)";
    private static final String GLIDER = "([1-9]\\d{0,3})";
    private static final String NON_GLIDER = "([A-Z]{3})";
    private static final String SPECIAL_PREFIX = "(X-)";
    private static final String SWISS_CIVIL_REGISTRATION = SPECIAL_PREFIX + "?" + SWISS_PREFIX + "(" + GLIDER + "|" + NON_GLIDER + ")";

    private static final String MILITARY_PREFIX = "(([ABCDJRTUVWXZ]|KAB)-)";
    private static final String MILITARY_REGISTRATION_NUMBER = "\\d{2,4}";
    private static final String MILITARY_REGISTRATION = MILITARY_PREFIX + MILITARY_REGISTRATION_NUMBER;

    private static final Pattern SWISS_CIVIL_PATTERN = Pattern.compile(SWISS_CIVIL_REGISTRATION);

    private static final Pattern SWISS_MILITARY_PATTERN = Pattern.compile(MILITARY_REGISTRATION);

    /**
     * <p>Singleton instance of this class.</p>
     */
    private static final AircraftRegistrationValidator AIRCRAFT_REGISTRATION = new AircraftRegistrationValidator();

    private AircraftRegistrationValidator() {
    }

    /**
     * <p>Returns the singleton instance of this validator.</p>
     *
     * @return singleton instance of this validator.
     */
    public static AircraftRegistrationValidator getInstance() {
        return AIRCRAFT_REGISTRATION;
    }

    /**
     * <p>Checks if a field has a valid aircraft registration.</p>
     *
     * @param aircraftRegistration The  value validation is performed on. A <code>null</code>
     *                     value is considered invalid.
     * @return if the license plate is a valid swiss license plate
     */
    public boolean isValid(String aircraftRegistration) {
        if (aircraftRegistration == null) {
            return false;
        }

        if (SWISS_MILITARY_PATTERN.matcher(aircraftRegistration).matches()) {
            return true;
        }

        return SWISS_CIVIL_PATTERN.matcher(aircraftRegistration).matches();
    }
}
