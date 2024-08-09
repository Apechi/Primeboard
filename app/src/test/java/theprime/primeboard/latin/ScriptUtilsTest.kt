// SPDX-License-Identifier: GPL-3.0-only
package theprime.primeboard.latin

import theprime.primeboard.latin.common.LocaleUtils.constructLocale
import theprime.primeboard.latin.utils.ScriptUtils.SCRIPT_CYRILLIC
import theprime.primeboard.latin.utils.ScriptUtils.SCRIPT_DEVANAGARI
import theprime.primeboard.latin.utils.ScriptUtils.SCRIPT_LATIN
import theprime.primeboard.latin.utils.ScriptUtils.script
import org.junit.Assert.assertEquals
import org.junit.Test

class ScriptUtilsTest {
    @Test fun defaultScript() {
        assertEquals(SCRIPT_LATIN, "en".constructLocale().script())
        assertEquals(SCRIPT_DEVANAGARI, "hi".constructLocale().script())
        assertEquals(SCRIPT_LATIN, "hi_zz".constructLocale().script())
        assertEquals(SCRIPT_LATIN, "sr-Latn".constructLocale().script())
        assertEquals(SCRIPT_CYRILLIC, "mk".constructLocale().script())
        assertEquals(SCRIPT_CYRILLIC, "fr-Cyrl".constructLocale().script())
    }
}
