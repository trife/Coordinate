package org.wheatgenetics.coordinate.tc.exclude;

/**
 * Uses:
 * android.app.Activity
 * android.content.DialogInterface
 * android.content.DialogInterface.OnClickListener
 * android.content.DialogInterface.OnMultiChoiceClickListener
 *
 * androidx.annotation.NonNull
 * androidx.annotation.StringRes
 *
 * org.wheatgenetics.androidlibrary.MultiChoiceItemsAlertDialog
 *
 * org.wheatgenetics.coordinate.R
 */
class ExcludedRowsOrColsAlertDialog
extends org.wheatgenetics.androidlibrary.MultiChoiceItemsAlertDialog
{
    @java.lang.SuppressWarnings({"UnnecessaryInterfaceModifier"}) interface Handler
    {
        public abstract void excludeRowsOrCols(
        @java.lang.SuppressWarnings({"CStyleArrayDeclaration"})
        @androidx.annotation.NonNull boolean checkedItems[]);
    }

    // region Fields
                                 private final java.lang.String label;
    @androidx.annotation.NonNull private final
        org.wheatgenetics.coordinate.tc.exclude.ExcludedRowsOrColsAlertDialog.Handler handler;

    private boolean titleHasBeenSet = false;
    // endregion

    private void excludeRowsOrCols(@java.lang.SuppressWarnings({"CStyleArrayDeclaration"})
    @androidx.annotation.NonNull final boolean checkedItems[])
    { this.handler.excludeRowsOrCols(checkedItems); }

    ExcludedRowsOrColsAlertDialog(final android.app.Activity activity,
    @androidx.annotation.StringRes final int label,
    @androidx.annotation.NonNull   final
        org.wheatgenetics.coordinate.tc.exclude.ExcludedRowsOrColsAlertDialog.Handler handler)
    { super(activity); this.label = this.getString(label); this.handler = handler; }

    void show(
    @java.lang.SuppressWarnings({"CStyleArrayDeclaration"}) final java.lang.String items       [],
    @java.lang.SuppressWarnings({"CStyleArrayDeclaration"}) final boolean          checkedItems[])
    {
        if (null != items && null != checkedItems)
        {
            if (!this.titleHasBeenSet)
            {
                this.setTitle(this.getString(
                    org.wheatgenetics.coordinate.R.string.ExcludedRowsOrColsAlertDialogTitle) +
                    " - " + this.label + 's');
                this.titleHasBeenSet = true;
            }

            this.setOKPositiveButton(new android.content.DialogInterface.OnClickListener()
                {
                    @java.lang.Override public void onClick(
                    final android.content.DialogInterface dialog, final int which)
                    {
                        org.wheatgenetics.coordinate.tc.exclude.ExcludedRowsOrColsAlertDialog
                            .this.excludeRowsOrCols(checkedItems);
                    }
                });
            this.show(items, checkedItems,
                new android.content.DialogInterface.OnMultiChoiceClickListener()
                {
                    @java.lang.Override public void onClick(
                    final android.content.DialogInterface dialog, final int which,
                    final boolean isChecked) { checkedItems[which] = isChecked; }
                });
        }
    }
}