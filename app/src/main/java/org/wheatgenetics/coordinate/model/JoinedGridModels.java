package org.wheatgenetics.coordinate.model;

/**
 * Uses:
 * android.util.Log
 *
 * org.wheatgenetics.coordinate.model.JoinedGridModel
 */
@java.lang.SuppressWarnings("ClassExplicitlyExtendsObject")
public class JoinedGridModels extends java.lang.Object
implements java.lang.Iterable<org.wheatgenetics.coordinate.model.JoinedGridModel>
{
    private java.util.ArrayList<org.wheatgenetics.coordinate.model.JoinedGridModel>
        arrayListInstance = null;

    // region Private Methods
    @java.lang.SuppressWarnings("Convert2Diamond")
    private java.util.ArrayList<org.wheatgenetics.coordinate.model.JoinedGridModel> arrayList()
    {
        if (null == this.arrayListInstance) this.arrayListInstance =
            new java.util.ArrayList<org.wheatgenetics.coordinate.model.JoinedGridModel>();
        return this.arrayListInstance;
    }

    @java.lang.SuppressWarnings("SimplifiableConditionalExpression")
    private boolean isInRange(final int i)
    {
        return i < 0 ? false : null == this.arrayListInstance ?
            false : i < this.arrayListInstance.size();
    }

    private static int logInfo(final java.lang.String msg)
    { return android.util.Log.i("JoinedGridModels", msg); }
    // endregion

    // region java.lang.Iterable<> Overridden Method
    @java.lang.Override
    public java.util.Iterator<org.wheatgenetics.coordinate.model.JoinedGridModel> iterator()
    {
        class Iterator extends java.lang.Object
        implements java.util.Iterator<org.wheatgenetics.coordinate.model.JoinedGridModel>
        {
            private final java.util.ListIterator<org.wheatgenetics.coordinate.model.JoinedGridModel>
                listIterator;

            private Iterator(
            final java.util.ArrayList<org.wheatgenetics.coordinate.model.JoinedGridModel> arrayList)
            { super(); assert null != arrayList; this.listIterator = arrayList.listIterator(); }

            @java.lang.Override
            public boolean hasNext() { return this.listIterator.hasNext(); }

            @java.lang.Override
            public org.wheatgenetics.coordinate.model.JoinedGridModel next()
            { return this.listIterator.next(); }

            @java.lang.Override
            public void remove() { throw new java.lang.UnsupportedOperationException(); }
        }
        return new Iterator(this.arrayList());
    }
    // endregion

    // region Public Methods
    @java.lang.SuppressWarnings("SimplifiableConditionalExpression")
    public boolean add(
    final org.wheatgenetics.coordinate.model.JoinedGridModel joinedGridModel)
    { return null == joinedGridModel ? false : this.arrayList().add(joinedGridModel); }

    public int size()
    { return null == this.arrayListInstance ? 0 : this.arrayListInstance.size(); }

    public java.lang.String[] names()
    {
        if (null == this.arrayListInstance)
            return null;
        else
        {
            final java.util.ArrayList<org.wheatgenetics.coordinate.model.JoinedGridModel>
                arrayList = this.arrayList();
            final int size = arrayList.size();

            if (size <= 0)
                return null;
            else
            {
                final java.lang.String result[] = new java.lang.String[size];
                {
                    final int first = 0, last = size - 1;
                    for (int i = first; i <= last; i++)
                    {
                        final org.wheatgenetics.coordinate.model.JoinedGridModel joinedGridModel =
                            arrayList.get(i);
                        assert null != joinedGridModel; result[i] = joinedGridModel.name();
                    }
                }
                return result;
            }
        }
    }

    public long[] indexes()
    {
        if (null == this.arrayListInstance)
            return null;
        else
        {
            final java.util.ArrayList<org.wheatgenetics.coordinate.model.JoinedGridModel>
                arrayList = this.arrayList();
            final int size = arrayList.size();

            if (size <= 0)
                return null;
            else
            {
                final long result[] = new long[size];
                {
                    final int first = 0, last = size - 1;
                    for (int i = first; i <= last; i++)
                    {
                        final org.wheatgenetics.coordinate.model.JoinedGridModel joinedGridModel =
                            arrayList.get(i);
                        assert null != joinedGridModel; result[i] = joinedGridModel.getId();
                    }
                }
                return result;
            }
        }
    }

    public org.wheatgenetics.coordinate.model.JoinedGridModel get(final int i)
    {
        if (null == this.arrayListInstance)
            return null;
        else
            return this.isInRange(i) ? this.arrayListInstance.get(i) : null;
    }

    public void logInfo()
    {
        if (null == this.arrayListInstance)
            org.wheatgenetics.coordinate.model.JoinedGridModels.logInfo(
                "null == this.arrayListInstance");
        else
            if (this.size() <= 0)
                org.wheatgenetics.coordinate.model.JoinedGridModels.logInfo("this.size() <= 0");
            else
                for (final org.wheatgenetics.coordinate.model.JoinedGridModel joinedGridModel: this)
                    org.wheatgenetics.coordinate.model.JoinedGridModels.logInfo(
                        joinedGridModel.toString());
    }
    // endregion
}