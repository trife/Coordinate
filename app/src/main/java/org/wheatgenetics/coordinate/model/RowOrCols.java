package org.wheatgenetics.coordinate.model;

/**
 * Uses:
 * android.support.annotation.IntRange
 *
 * org.json.JSONArray
 * org.json.JSONException
 * org.json.JSONTokener
 *
 * org.wheatgenetics.coordinate.model.RowOrCol
 */
@java.lang.SuppressWarnings("ClassExplicitlyExtendsObject")
class RowOrCols extends java.lang.Object
{
    private java.util.TreeSet<org.wheatgenetics.coordinate.model.RowOrCol>
        rowOrColTreeSetInstance = null;

    @java.lang.SuppressWarnings("Convert2Diamond")
    private java.util.TreeSet<org.wheatgenetics.coordinate.model.RowOrCol> rowOrColTreeSet()
    {
        if (null == this.rowOrColTreeSetInstance) this.rowOrColTreeSetInstance =
            new java.util.TreeSet<org.wheatgenetics.coordinate.model.RowOrCol>();
        return this.rowOrColTreeSetInstance;
    }

    // region Constructors
    RowOrCols() { super(); }

    RowOrCols(final java.lang.String json)
    {
        this();

        if (null != json) if (json.trim().length() > 0)
        {
            org.json.JSONArray jsonArray;
            {
                final org.json.JSONTokener jsonTokener = new org.json.JSONTokener(json);
                try
                {
                    jsonArray = (org.json.JSONArray) jsonTokener.nextValue();   // throws org.json.-
                }                                                               //  JSONException
                catch (final org.json.JSONException e)
                { return; /* Leave rowOrColTreeSetInstance == null. */ }
            }

            assert null != jsonArray; final int length = jsonArray.length();
            if (length > 0)
            {
                final int first = 0, last = length - 1;
                for (int i = first; i <= last; i++)
                    try { this.add(jsonArray.getInt(i) /* throws org.json.JSONException */); }
                    catch (final org.json.JSONException e) { /* Skip ith jsonObject. */ }
            }
        }
    }
    // endregion

    // region Overridden Methods
    @java.lang.Override
    public java.lang.String toString()
    {
        if (null == this.rowOrColTreeSetInstance)
            return "null";
        else
            if (this.rowOrColTreeSetInstance.isEmpty())
                return "empty";
            else
            {
                final java.lang.StringBuilder result = new java.lang.StringBuilder();
                {
                    boolean firstValue = true;
                    for (final org.wheatgenetics.coordinate.model.RowOrCol rowOrCol:
                    this.rowOrColTreeSetInstance)
                        if (null != rowOrCol)
                        {
                            if (firstValue) firstValue = false; else result.append(", ");
                            result.append(rowOrCol.toString());
                        }
                }
                return result.toString();
            }
    }

    @java.lang.Override @java.lang.SuppressWarnings("SimplifiableIfStatement")
    public boolean equals(final java.lang.Object object)
    {
        if (null == object)
            return false;
        else
            if (object instanceof org.wheatgenetics.coordinate.model.RowOrCols)
            {
                final org.wheatgenetics.coordinate.model.RowOrCols rowOrCols =
                    (org.wheatgenetics.coordinate.model.RowOrCols) object;

                if (null == this.rowOrColTreeSetInstance
                &&  null != rowOrCols.rowOrColTreeSetInstance)
                    return false;
                else
                    if (null != this.rowOrColTreeSetInstance
                    &&  null == rowOrCols.rowOrColTreeSetInstance) return false;

                if (null == this.rowOrColTreeSetInstance)
                    return true;
                else
                    return this.rowOrColTreeSetInstance.equals(rowOrCols.rowOrColTreeSetInstance);
            }
            else return false;
    }

    @java.lang.Override
    public int hashCode() { return this.toString().hashCode(); }

    @java.lang.Override @java.lang.SuppressWarnings({"CloneDoesntCallSuperClone",
        "CloneDoesntDeclareCloneNotSupportedException"})
    protected java.lang.Object clone()
    {
        final org.wheatgenetics.coordinate.model.RowOrCols result =
            new org.wheatgenetics.coordinate.model.RowOrCols();

        if (null != this.rowOrColTreeSetInstance) result.rowOrColTreeSetInstance =
            new java.util.TreeSet<org.wheatgenetics.coordinate.model.RowOrCol>(
                this.rowOrColTreeSetInstance);

        return result;
    }
    // endregion

    // region Package Methods
    void add(@android.support.annotation.IntRange(from = 1) final int value)
    { this.rowOrColTreeSet().add(new org.wheatgenetics.coordinate.model.RowOrCol(value)); }

    java.lang.String json()
    {
        if (null == this.rowOrColTreeSetInstance)
            return null;
        else
        {
            final java.util.TreeSet<org.wheatgenetics.coordinate.model.RowOrCol> rowOrColTreeSet =
                this.rowOrColTreeSet();

            if (rowOrColTreeSet.isEmpty())
                return null;
            else
            {
                final org.json.JSONArray jsonArray = new org.json.JSONArray();

                for (final org.wheatgenetics.coordinate.model.RowOrCol rowOrCol: rowOrColTreeSet)
                    if (null != rowOrCol) jsonArray.put(rowOrCol.getValue());

                return jsonArray.toString();
            }
        }
    }

    void clear() { if (null != this.rowOrColTreeSetInstance) this.rowOrColTreeSetInstance.clear(); }

    @java.lang.SuppressWarnings("SimplifiableIfStatement")
    boolean contains(@android.support.annotation.IntRange(from = 1) final int candidateValue)
    {
        if (null == this.rowOrColTreeSetInstance)
            return false;
        else
            return this.rowOrColTreeSetInstance.contains(
                new org.wheatgenetics.coordinate.model.RowOrCol(candidateValue));
    }
    // endregion
}