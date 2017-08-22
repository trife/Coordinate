package org.wheatgenetics.coordinate.optionalField;

/**
 * Uses:
 * org.wheatgenetics.javalib.Utils
 *
 * org.wheatgenetics.coordinate.optionalField.OptionalField
 */
abstract class OptionalFields extends java.lang.Object
implements java.lang.Iterable<org.wheatgenetics.coordinate.optionalField.OptionalField>
{
    /**
     * Iterates over non-null optional fields.
     */
    static class Iterator extends java.lang.Object
    implements java.util.Iterator<org.wheatgenetics.coordinate.optionalField.OptionalField>
    {
        // region Fields
        final java.util.ArrayList<org.wheatgenetics.coordinate.optionalField.OptionalField>
            arrayList;
        final java.util.ListIterator<org.wheatgenetics.coordinate.optionalField.OptionalField>
            listIterator;
        // endregion

        Iterator(final java.util.ArrayList<
        org.wheatgenetics.coordinate.optionalField.OptionalField> arrayList)
        {
            super();

            this.arrayList = arrayList;

            assert null != this.arrayList;
            this.listIterator = this.arrayList.listIterator();
        }

        // region Overridden Methods
        @java.lang.Override
        public boolean hasNext()
        {
            assert null != this.listIterator; assert null != this.arrayList;
            while (this.listIterator.hasNext())
            {
                final org.wheatgenetics.coordinate.optionalField.OptionalField optionalField =
                    this.arrayList.get(this.listIterator.nextIndex());
                if (null == optionalField) this.listIterator.next(); else return true;
            }
            return false;
        }

        @java.lang.Override
        public org.wheatgenetics.coordinate.optionalField.OptionalField next()
        {
            org.wheatgenetics.coordinate.optionalField.OptionalField result;

            assert null != this.listIterator;
            do result = this.listIterator.next(); while (null == result);
            return result;
        }

        @java.lang.Override
        public void remove() { throw new java.lang.UnsupportedOperationException(); }
        // endregion
    }

    java.util.ArrayList<org.wheatgenetics.coordinate.optionalField.OptionalField> arrayList =
        new java.util.ArrayList<org.wheatgenetics.coordinate.optionalField.OptionalField>();

    // region Overridden Methods
    @java.lang.Override
    public boolean equals(final java.lang.Object o)
    {
        if (null == o)
            return false;
        else
            if (o instanceof org.wheatgenetics.coordinate.optionalField.OptionalFields)
            {
                final org.wheatgenetics.coordinate.optionalField.OptionalFields f =
                    (org.wheatgenetics.coordinate.optionalField.OptionalFields) o;

                assert null != this.arrayList; assert null != f.arrayList;
                if (this.arrayList.size() != f.arrayList.size())
                    return false;
                else
                {
                    {
                        int i = 0;
                        for (final org.wheatgenetics.coordinate.optionalField.OptionalField
                        optionalField: this)
                            if (!optionalField.equals(f.arrayList.get(i++))) return false;
                    }
                    return true;
                }
            }
            else return false;
    }

    @java.lang.Override
    public int hashCode()
    {
        java.lang.StringBuffer signatures = null;
        {
            boolean firstOptionalField = true;
            for (final org.wheatgenetics.coordinate.optionalField.OptionalField optionalField: this)
            {
                java.lang.String signature;
                try { signature = optionalField.makeJSONObject().toString(); }
                catch (final org.json.JSONException e) { continue; }

                if (firstOptionalField)
                    signatures = new java.lang.StringBuffer(signature);
                else
                {
                    signatures.append('\n' + signature);
                    firstOptionalField = false;
                }
            }
        }
        return org.wheatgenetics.javalib.Utils.makeEmptyIfNull(signatures.toString()).hashCode();
    }
    // endregion

    // region java.lang.Iterable<> Overridden Method
    @java.lang.Override
    public org.wheatgenetics.coordinate.optionalField.OptionalFields.Iterator iterator()
    {
        return
            new org.wheatgenetics.coordinate.optionalField.OptionalFields.Iterator(this.arrayList);
    }
    // endregion
}