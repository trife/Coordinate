package org.wheatgenetics.coordinate.display;

/**
 * Uses:
 * android.annotation.SuppressLint
 * android.app.Activity
 * android.view.LayoutInflater
 * android.widget.LinearLayout
 * android.widget.TextView
 *
 * org.wheatgenetics.coordinate.model.EntryModel
 *
 * org.wheatgenetics.coordinate.R
 *
 * org.wheatgenetics.coordinate.display.Element
 * org.wheatgenetics.coordinate.display.Element.Handler
 */
@java.lang.SuppressWarnings("ClassExplicitlyExtendsObject")
class Elements extends java.lang.Object
implements org.wheatgenetics.coordinate.display.Element.Handler
{
    // region Fields
    private final android.app.Activity                                 activity;
    private final org.wheatgenetics.coordinate.display.Element.Handler handler ;

    private org.wheatgenetics.coordinate.display.Element elementArray[][];

    private int activeRow, activeCol;
    // endregion

    Elements(final android.app.Activity activity, final int rows, final int cols,
    final int activeRow, final int activeCol,
    final org.wheatgenetics.coordinate.display.Element.Handler handler)
    {
        super();

        this.activity = activity; this.handler = handler;
        this.allocate(rows, cols, activeRow, activeCol);
    }

    // region org.wheatgenetics.coordinate.display.Element.Handler Overridden Methods
    @java.lang.Override
    public void toggle(final org.wheatgenetics.coordinate.model.EntryModel entryModel)
    { assert null != this.handler; this.handler.toggle(entryModel); }

    @java.lang.Override
    public void activate(final org.wheatgenetics.coordinate.display.Element element)
    {
        if (null != element)
        {
            final int newActiveRow = element.getRow(), newActiveCol = element.getCol();
            if (newActiveRow != this.activeRow || newActiveCol != this.activeCol)
            {
                if (this.activeRow > -1 && this.activeCol > -1)
                {
                    assert null != this.elementArray;
                    this.elementArray[this.activeRow][this.activeCol].inactivate();
                }

                this.activeRow = newActiveRow; this.activeCol = newActiveCol;

                assert null != this.handler; this.handler.activate(element);
            }
        }
    }
    // endregion

    // region Package Methods
    void allocate(final int rows, final int cols, final int activeRow, final int activeCol)
    {
        this.elementArray = new org.wheatgenetics.coordinate.display.Element[rows][cols];
        this.activeRow    = activeRow;                        this.activeCol = activeCol;
    }

    void clear() { this.elementArray = null; this.activeRow = this.activeCol = -1; }

    @android.annotation.SuppressLint("InflateParams")
    android.widget.LinearLayout add(final org.wheatgenetics.coordinate.model.EntryModel entryModel)
    {
        if (null == entryModel)
            return null;
        else
        {
            final android.widget.LinearLayout result;
            {
                assert null != this.activity;
                final android.view.LayoutInflater layoutInflater =
                    this.activity.getLayoutInflater();

                result = (android.widget.LinearLayout) layoutInflater.inflate(
                    org.wheatgenetics.coordinate.R.layout.display_table_cell, null);
            }
            {
                assert null != result;
                final org.wheatgenetics.coordinate.display.Element element =
                    new org.wheatgenetics.coordinate.display.Element(
                        /* context    => */ this.activity,
                        /* entryModel => */ entryModel   ,
                        /* textView   => */ (android.widget.TextView)
                            result.findViewById(org.wheatgenetics.coordinate.R.id.displayTextView),
                        /* handler   => */ this          ,
                        /* activeRow => */ this.activeRow,
                        /* activeCol => */ this.activeCol);
                assert null != this.elementArray;
                this.elementArray[element.getRow()][element.getCol()] = element;
            }
            return result;
        }
    }
    // endregion
}