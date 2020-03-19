package org.wheatgenetics.coordinate.display.adapter;

/**
 * Uses:
 * android.content.Context
 * android.widget.LinearLayout
 *
 * androidx.annotation.IdRes
 * androidx.annotation.IntRange
 * androidx.annotation.NonNull
 * androidx.annotation.RestrictTo
 * androidx.annotation.RestrictTo.Scope
 *
 * org.wheatgenetics.coordinate.R
 * org.wheatgenetics.coordinate.Utils
 *
 * org.wheatgenetics.coordinate.display.adapter.TopOrLeftViewHolder
 */
class LeftViewHolder extends org.wheatgenetics.coordinate.display.adapter.TopOrLeftViewHolder
{
    // region Constructors
    @androidx.annotation.RestrictTo(androidx.annotation.RestrictTo.Scope.SUBCLASSES)
    LeftViewHolder(
    @androidx.annotation.NonNull final android.content.Context     context ,
    @androidx.annotation.NonNull final android.widget.LinearLayout itemView  ,
    @androidx.annotation.IdRes   final int                         textViewId)
    { super(context, itemView, textViewId); }

    LeftViewHolder(
    @androidx.annotation.NonNull final android.content.Context     context ,
    @androidx.annotation.NonNull final android.widget.LinearLayout itemView)
    { this(context, itemView, org.wheatgenetics.coordinate.R.id.leftDisplayTextView); }
    // endregion

    void bind(@androidx.annotation.IntRange(from = 1) final int rowOrCol, final boolean numbering)
    {
        this.bind(numbering ? java.lang.Integer.toString(rowOrCol) :
            org.wheatgenetics.coordinate.Utils.convert(rowOrCol - 1));
    }
}