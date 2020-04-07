package org.wheatgenetics.coordinate.model;

/**
 * Uses:
 * org.junit.Assert
 * org.junit.Test
 *
 * androidx.annotation.Nullable
 * androidx.annotation.StringRes
 *
 * org.wheatgenetics.coordinate.R
 * org.wheatgenetics.coordinate.StringGetter
 *
 * org.wheatgenetics.coordinate.model.TemplateModel
 */
@java.lang.SuppressWarnings({"ClassExplicitlyExtendsObject"}) public class TemplateModelTest
extends java.lang.Object implements org.wheatgenetics.coordinate.StringGetter
{
    // region org.wheatgenetics.coordinate.StringGetter Overridden Method
    @java.lang.Override @androidx.annotation.Nullable public java.lang.String get(
    @androidx.annotation.StringRes final int resId)
    {
        switch (resId)
        {
            case org.wheatgenetics.coordinate.R.string.BaseOptionalFieldPersonFieldName:
                return "Person";

            case org.wheatgenetics.coordinate.R.string.BaseOptionalFieldNameFieldName:
                return "Name";

            case org.wheatgenetics.coordinate.R.string.BaseOptionalFieldIdentificationFieldName:
                return "Identification";

            default: return null;
        }
    }
    // endregion

    // region Overridden Method Tests
    @org.junit.Test() public void toStringSucceeds()
    {
        final java.lang.String expectedString =
            "TemplateModel [id: 03, title=testTitle, type=1, rows=5, cols=2, generatedExcludedCel" +
            "lsAmount=0, colNumbering=true, rowNumbering=false, entryLabel=null, stamp=0, exclude" +
            "dCells=null, excludedRows=null, excludedCols=null, options=]";
        final org.wheatgenetics.coordinate.model.TemplateModel templateModel =
            new org.wheatgenetics.coordinate.model.TemplateModel(
                /* id                           => */3,
                /* title                        => */"testTitle",
                /* code                         => */1,
                /* rows                         => */5,
                /* cols                         => */2,
                /* generatedExcludedCellsAmount => */0,
                /* excludedCells                => */null,
                /* excludedRows                 => */null,
                /* excludedCols                 => */null,
                /* colNumbering                 => */1,
                /* rowNumbering                 => */0,
                /* entryLabel                   => */null,
                /* optionalFields               => */null,
                /* stringGetter                 => */this,
                /* timestamp                    => */0);
        org.junit.Assert.assertEquals(expectedString, templateModel.toString());
    }

    @org.junit.Test() public void equalsAndHashCodeWork()
    {
        final long             id    = 44                                                       ;
        final java.lang.String title = "testTitle"                                              ;
        final int              code  = 1, rows = 5, cols = 2, colNumbering = 1, rowNumbering = 0;
        final long             timestamp = 0                                                    ;

        final org.wheatgenetics.coordinate.model.TemplateModel firstTemplateModel =
            new org.wheatgenetics.coordinate.model.TemplateModel(
                /* id                           => */ id   ,
                /* title                        => */ title,
                /* code                         => */ code ,
                /* rows                         => */ rows ,
                /* cols                         => */ cols ,
                /* generatedExcludedCellsAmount => */0,
                /* excludedCells                => */null,
                /* excludedRows                 => */null,
                /* excludedCols                 => */null,
                /* colNumbering                 => */ colNumbering,
                /* rowNumbering                 => */ rowNumbering,
                /* entryLabel                   => */null,
                /* optionalFields               => */null,
                /* stringGetter                 => */this,
                /* timestamp                    => */ timestamp);
        org.wheatgenetics.coordinate.model.TemplateModel secondTemplateModel =
            new org.wheatgenetics.coordinate.model.TemplateModel(
                /* id                           => */ id   ,
                /* title                        => */ title,
                /* code                         => */ code ,
                /* rows                         => */ rows ,
                /* cols                         => */ cols ,
                /* generatedExcludedCellsAmount => */0,
                /* excludedCells                => */null,
                /* excludedRows                 => */null,
                /* excludedCols                 => */null,
                /* colNumbering                 => */ colNumbering,
                /* rowNumbering                 => */ rowNumbering,
                /* entryLabel                   => */null,
                /* optionalFields               => */null,                   // Notice.
                /* stringGetter                 => */this,
                /* timestamp                    => */ timestamp);

        // noinspection SimplifiableJUnitAssertion
        org.junit.Assert.assertTrue  (firstTemplateModel.equals(secondTemplateModel));
        org.junit.Assert.assertEquals(
            firstTemplateModel.hashCode(), secondTemplateModel.hashCode());

        secondTemplateModel = new org.wheatgenetics.coordinate.model.TemplateModel(
            /* id                           => */ id   ,
            /* title                        => */ title,
            /* code                         => */ code ,
            /* rows                         => */ rows ,
            /* cols                         => */ cols ,
            /* generatedExcludedCellsAmount => */0,
            /* excludedCells                => */null,
            /* excludedRows                 => */null,
            /* excludedCols                 => */null,
            /* colNumbering                 => */ colNumbering,
            /* rowNumbering                 => */ rowNumbering,
            /* entryLabel                   => */null,
            /* optionalFields               => */"",                         // Notice.
            /* stringGetter                 => */this,
            /* timestamp                    => */ timestamp);

        // noinspection SimplifiableJUnitAssertion
        org.junit.Assert.assertTrue  (firstTemplateModel.equals(secondTemplateModel));
        org.junit.Assert.assertEquals(
            firstTemplateModel.hashCode(), secondTemplateModel.hashCode());

        secondTemplateModel = new org.wheatgenetics.coordinate.model.TemplateModel(
            /* id                           => */ id   ,
            /* title                        => */ title,
            /* code                         => */ code ,
            /* rows                         => */ rows ,
            /* cols                         => */ cols ,
            /* generatedExcludedCellsAmount => */0,
            /* excludedCells                => */null,
            /* excludedRows                 => */null,
            /* excludedCols                 => */null,
            /* colNumbering                 => */ colNumbering,
            /* rowNumbering                 => */ rowNumbering,
            /* entryLabel                   => */null,
            /* optionalFields               => */"  ",                       // Notice.
            /* stringGetter                 => */this,
            /* timestamp                    => */ timestamp);

        // noinspection SimplifiableJUnitAssertion
        org.junit.Assert.assertTrue  (firstTemplateModel.equals(secondTemplateModel));
        org.junit.Assert.assertEquals(
            firstTemplateModel.hashCode(), secondTemplateModel.hashCode());

        secondTemplateModel = new org.wheatgenetics.coordinate.model.TemplateModel(
            /* id                           => */ id   ,
            /* title                        => */ title,
            /* code                         => */ code ,
            /* rows                         => */ rows ,
            /* cols                         => */ cols ,
            /* generatedExcludedCellsAmount => */0,
            /* excludedCells                => */null,
            /* excludedRows                 => */null,
            /* excludedCols                 => */null,
            /* colNumbering                 => */ colNumbering,
            /* rowNumbering                 => */ rowNumbering,
            /* entryLabel                   => */null,
            /* optionalFields               => */null,
            /* stringGetter                 => */this,
            /* timestamp                    => */5087);                         // Notice.

        // noinspection SimplifiableJUnitAssertion
        org.junit.Assert.assertFalse    (firstTemplateModel.equals(secondTemplateModel));
        org.junit.Assert.assertNotEquals(
            firstTemplateModel.hashCode(), secondTemplateModel.hashCode());
    }

    @org.junit.Test() public void cloneSucceeds()
    {
        final org.wheatgenetics.coordinate.model.TemplateModel templateModel =
            new org.wheatgenetics.coordinate.model.TemplateModel(
                /* id                           => */12,
                /* title                        => */"testTitle",
                /* code                         => */1,
                /* rows                         => */5,
                /* cols                         => */2,
                /* generatedExcludedCellsAmount => */1,
                /* excludedCells                => */null,
                /* excludedRows                 => */null,
                /* excludedCols                 => */null,
                /* colNumbering                 => */1,
                /* rowNumbering                 => */0,
                /* entryLabel                   => */null,
                /* optionalFields               => */null,
                /* stringGetter                 => */this,
                /* timestamp                    => */0);
        final org.wheatgenetics.coordinate.model.TemplateModel clonedTemplateModel =
            (org.wheatgenetics.coordinate.model.TemplateModel) templateModel.clone();

        // noinspection SimplifiableJUnitAssertion
        org.junit.Assert.assertTrue(templateModel.equals(clonedTemplateModel));
    }
    // endregion

    // region export() Package Method Tests
    @org.junit.Test() public void exportWorks()
    {
        final java.lang.String expectedString =
            "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>\n"           +
            "<template>\n"                                                         +
            "    <title>testTitle</title>\n"                                       +
            "    <rows>5</rows>\n"                                                 +
            "    <cols>2</cols>\n"                                                 +
            "    <generatedExcludedCellsAmount>0</generatedExcludedCellsAmount>\n" +
            "    <colNumbering>true</colNumbering>\n"                              +
            "    <rowNumbering>false</rowNumbering>\n"                             +
            "</template>"                                                          ;
        final java.io.StringWriter stringWriter = new java.io.StringWriter();
        {
            final org.wheatgenetics.coordinate.model.TemplateModel templateModel =
                new org.wheatgenetics.coordinate.model.TemplateModel(
                    /* id                           => */3,
                    /* title                        => */"testTitle",
                    /* code                         => */1,
                    /* rows                         => */5,
                    /* cols                         => */2,
                    /* generatedExcludedCellsAmount => */0,
                    /* excludedCells                => */null,
                    /* excludedRows                 => */null,
                    /* excludedCols                 => */null,
                    /* colNumbering                 => */1,
                    /* rowNumbering                 => */0,
                    /* entryLabel                   => */null,
                    /* optionalFields               => */null,
                    /* stringGetter                 => */this,
                    /* timestamp                    => */0);
            org.junit.Assert.assertTrue(templateModel.export(stringWriter));
        }
        org.junit.Assert.assertEquals(expectedString, stringWriter.toString());
    }

    @org.junit.Test() public void entryLabelExportWorks()
    {
        final java.lang.String expectedString =
            "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>\n"           +
            "<template>\n"                                                         +
            "    <title>testTitle</title>\n"                                       +
            "    <rows>5</rows>\n"                                                 +
            "    <cols>2</cols>\n"                                                 +
            "    <generatedExcludedCellsAmount>0</generatedExcludedCellsAmount>\n" +
            "    <colNumbering>true</colNumbering>\n"                              +
            "    <rowNumbering>false</rowNumbering>\n"                             +
            "    <entryLabel>testEntryLabel</entryLabel>\n"                        +
            "</template>"                                                          ;
        final java.io.StringWriter stringWriter = new java.io.StringWriter();
        {
            final org.wheatgenetics.coordinate.model.TemplateModel templateModel =
                new org.wheatgenetics.coordinate.model.TemplateModel(
                    /* id                           => */3,
                    /* title                        => */"testTitle",
                    /* code                         => */1,
                    /* rows                         => */5,
                    /* cols                         => */2,
                    /* generatedExcludedCellsAmount => */0,
                    /* excludedCells                => */null,
                    /* excludedRows                 => */null,
                    /* excludedCols                 => */null,
                    /* colNumbering                 => */1,
                    /* rowNumbering                 => */0,
                    /* entryLabel                   => */null,
                    /* optionalFields               => */null,
                    /* stringGetter                 => */this,
                    /* timestamp                    => */0);
            templateModel.setEntryLabel("testEntryLabel");
            org.junit.Assert.assertTrue(templateModel.export(stringWriter));
        }
        org.junit.Assert.assertEquals(expectedString, stringWriter.toString());
    }
    // endregion

    @org.junit.Test() public void optionalFieldsMethodsSucceed()
    {
        final org.wheatgenetics.coordinate.model.TemplateModel templateModel =
            new org.wheatgenetics.coordinate.model.TemplateModel(
                /* id                           => */10,
                /* title                        => */"testTitle",
                /* code                         => */1,
                /* rows                         => */5,
                /* cols                         => */5,
                /* generatedExcludedCellsAmount => */0,
                /* excludedCells                => */null,
                /* excludedRows                 => */null,
                /* excludedCols                 => */null,
                /* colNumbering                 => */1,
                /* rowNumbering                 => */0,
                /* entryLabel                   => */null,
                /* optionalFields               => */null,
                /* stringGetter                 => */this,
                /* timestamp                    => */880);
        org.junit.Assert.assertNull(templateModel.optionalFields       ());
        org.junit.Assert.assertTrue(templateModel.optionalFieldsIsEmpty());
        org.junit.Assert.assertNull(templateModel.optionalFieldsClone  ());
        org.junit.Assert.assertNull(templateModel.optionalFieldsAsJson ());
    }
}