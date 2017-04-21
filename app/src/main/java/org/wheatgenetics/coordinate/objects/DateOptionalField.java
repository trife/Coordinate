package org.wheatgenetics.coordinate.objects;

class DateOptionalField extends org.wheatgenetics.coordinate.objects.OptionalField
{
    // region Constructors
    DateOptionalField(final java.lang.String name)
    {
        super(name, /* hint => */ org.wheatgenetics.coordinate.objects.OptionalField.DATE_HINT);
    }

    DateOptionalField(final org.json.JSONObject jsonObject) throws org.json.JSONException
    {
        super(jsonObject);
    }
    // endregion


    @Override
    public java.lang.String getValue()
    {
        return org.wheatgenetics.coordinate.utils.Utils.getCurrentDate();
    }
}