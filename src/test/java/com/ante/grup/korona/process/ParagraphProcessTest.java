package com.ante.grup.korona.process;

import com.ante.grup.korona.model.KoronaData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphProcessTest {
    private ParagraphProcess paragraphProcess;
    private String PROPER_INPUT_STRING = "20.04.2020 tarihinde Ankara da Korona virüs salgınında yapılan testlerde 15 yeni vaka bulundu. 15 kişi korona dan vefat etti. 15 kişide taburcu oldu.";
    private String PROPER_INPUT_STRING_NON_NUMERIC = "20.04.2020 tarihinde Ankara da Korona virüs salgınında yapılan testlerde yeni vaka bulunmadı. Vefat olmadı. Kimse taburcu olmadı.";
    private int PROPER_STRING_NUMERIC_VALUES = 15;
    private int ZERO = 0;

    @BeforeEach
    void setParagraphProcess() {
        paragraphProcess = new ParagraphProcess();
    }

    @Test
    public void when_ParagraphIsProperly_AllKoronaDataObjectMustBeFilled() {
        KoronaData koronaDataObj = paragraphProcess.getKoronaDataObj(PROPER_INPUT_STRING);
        assertNotNull(koronaDataObj.getSehir(), "sehir must be not null");
        assertNotNull(koronaDataObj.getTarih(), "tarih must be not null");
        assertEquals(koronaDataObj.getTaburcu(),PROPER_STRING_NUMERIC_VALUES, "taburcu value must be equal");
        assertEquals(koronaDataObj.getVaka(), PROPER_STRING_NUMERIC_VALUES,"vaka value must be equal");
        assertEquals(koronaDataObj.getVefat(), PROPER_STRING_NUMERIC_VALUES,"vefat value must be equal");
    }

    @Test
    public void when_ParagraphIsProperly_But_NumericValuesNotExist_NumericValuesSetZero() {
        KoronaData koronaDataObj = paragraphProcess.getKoronaDataObj(PROPER_INPUT_STRING_NON_NUMERIC);
        assertNotNull(koronaDataObj.getSehir(), "sehir must be not null");
        assertNotNull(koronaDataObj.getTarih(), "tarih must be not null");
        assertEquals(koronaDataObj.getTaburcu(),ZERO, "taburcu values must be equal");
        assertEquals(koronaDataObj.getVaka(), ZERO,"vaka values must be equal");
        assertEquals(koronaDataObj.getVefat(), ZERO,"vefat values must be equal");
    }
}