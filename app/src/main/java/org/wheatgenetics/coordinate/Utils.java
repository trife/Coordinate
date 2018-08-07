package org.wheatgenetics.coordinate;

/**
 * Uses:
 * android.app.AlertDialog.Builder
 * android.content.Context
 * android.content.DialogInterface
 * android.content.DialogInterface.OnClickListener
 * android.content.SharedPreferences
 * android.preference.PreferenceManager
 * android.support.annotation.IntRange
 * android.support.annotation.StringRes
 *
 * org.wheatgenetics.androidlibrary.Utils
 *
 * org.wheatgenetics.coordinate.R
 */
@java.lang.SuppressWarnings({"ClassExplicitlyExtendsObject"})
public class Utils extends java.lang.Object
{
    // region Types
    public enum Advancement   { ERROR, DOWN_THEN_ACROSS , ACROSS_THEN_DOWN        }
           enum ProjectExport { ERROR, ONE_FILE_PER_GRID, ONE_FILE_ENTIRE_PROJECT }
    // endregion

    private static android.content.SharedPreferences defaultSharedPreferencesInstance = null;

    // region AlertDialog Methods
    // region Overview
    // private alert(String title, String message, positiveText, positiveListener, negativeListener)
    //     public alert(int title, String message)
    //         public alert(int message)
    //     public alert(int message, yesRunnable)
    //     private confirm(int title, String message, yesRunnable, noRunnable)
    //         public confirm(int title, String message, yesRunnable)
    //         private confirm(int title, int message, yesRunnable, noRunnable)
    //             confirm(int title, int message, yesRunnable)
    //                 confirm(int message, yesRunnable)
    //             public confirm(int message, yesRunnable, noRunnable)
    // endregion

    // region alert() AlertDialog Methods
    private static void alert(final android.content.Context context, final java.lang.String title,
    final java.lang.String message, final java.lang.String positiveButtonText,
    final android.content.DialogInterface.OnClickListener positiveButtonOnClickListener,
    final android.content.DialogInterface.OnClickListener negativeButtonOnClickListener)
    {
        final android.app.AlertDialog.Builder builder =
            new android.app.AlertDialog.Builder(context);
        if (null != negativeButtonOnClickListener)
            builder.setNegativeButton("No", negativeButtonOnClickListener);
        builder.setTitle(title).setMessage(message).setPositiveButton(positiveButtonText,
            positiveButtonOnClickListener).show();
    }

    // region alert(title, message) AlertDialog Methods
    public static void alert(final android.content.Context context,
    @android.support.annotation.StringRes final int title, final java.lang.String message)
    {
        assert null != context;
        org.wheatgenetics.coordinate.Utils.alert(context, context.getString(title), message,
            "Ok",
            org.wheatgenetics.androidlibrary.Utils.cancellingOnClickListener(),
            null);
    }

    public static void alert(final android.content.Context context,
    @android.support.annotation.StringRes final int message)
    {
        assert null != context; org.wheatgenetics.coordinate.Utils.alert(context,
            org.wheatgenetics.coordinate.R.string.app_name, context.getString(message));
    }
    // endregion

    // region alert(title, message, yesRunnable) AlertDialog Method
    public static void alert(final android.content.Context context,
    @android.support.annotation.StringRes final int message, final java.lang.Runnable yesRunnable)
    {
        assert null != context; org.wheatgenetics.coordinate.Utils.alert(context,
            context.getString(org.wheatgenetics.coordinate.R.string.app_name),
            context.getString(message),"Ok",
            new android.content.DialogInterface.OnClickListener()
            {
                @java.lang.Override
                public void onClick(final android.content.DialogInterface dialog, final int id)
                {
                    assert null != dialog; dialog.cancel();
                    if (null != yesRunnable) yesRunnable.run();
                }
            },null);
    }
    // endregion
    // endregion

    // region confirm() AlertDialog Methods
    private static void confirm(final android.content.Context context,
    @android.support.annotation.StringRes final int title, final java.lang.String message,
    final java.lang.Runnable yesRunnable, final java.lang.Runnable noRunnable)
    {
        assert null != context;
        org.wheatgenetics.coordinate.Utils.alert(context, context.getString(title),
            message,"Yes", new android.content.DialogInterface.OnClickListener()
            {
                @java.lang.Override
                public void onClick(final android.content.DialogInterface dialog, final int id)
                {
                    assert null != dialog; dialog.cancel();
                    if (null != yesRunnable) yesRunnable.run();
                }
            }, new android.content.DialogInterface.OnClickListener()
            {
                @java.lang.Override
                public void onClick(final android.content.DialogInterface dialog, final int id)
                {
                    assert null != dialog; dialog.cancel();
                    if (null != noRunnable) noRunnable.run();
                }
            });
    }

    // region confirm(title, String message, yesRunnable) AlertDialog Methods
    public static void confirm(final android.content.Context context,
    @android.support.annotation.StringRes final int title, final java.lang.String message,
    final java.lang.Runnable yesRunnable)
    {
        org.wheatgenetics.coordinate.Utils.confirm(
            context, title, message, yesRunnable,null);
    }
    // endregion

    private static void confirm(final android.content.Context context,
    @android.support.annotation.StringRes final int title  ,
    @android.support.annotation.StringRes final int message,
    final java.lang.Runnable yesRunnable, final java.lang.Runnable noRunnable)
    {
        assert null != context;
        org.wheatgenetics.coordinate.Utils.confirm(context, title,
            context.getString(message), yesRunnable, noRunnable);
    }

    // region confirm(title, int message, yesRunnable) AlertDialog Methods
    public static void confirm(final android.content.Context context,
    @android.support.annotation.StringRes final int title  ,
    @android.support.annotation.StringRes final int message,
    final java.lang.Runnable yesRunnable)
    {
        org.wheatgenetics.coordinate.Utils.confirm(
            context, title, message, yesRunnable,null);
    }

    public static void confirm(final android.content.Context context,
    @android.support.annotation.StringRes final int message, final java.lang.Runnable yesRunnable)
    {
        org.wheatgenetics.coordinate.Utils.confirm(context,
            org.wheatgenetics.coordinate.R.string.app_name, message, yesRunnable);
    }
    // endregion

    // region confirm(title, message, yesRunnable, noRunnable) AlertDialog Method
    public static void confirm(final android.content.Context context,
    @android.support.annotation.StringRes final int message, final java.lang.Runnable yesRunnable,
    final java.lang.Runnable noRunnable)
    {
        org.wheatgenetics.coordinate.Utils.confirm(context,
            org.wheatgenetics.coordinate.R.string.app_name, message, yesRunnable, noRunnable);
    }
    // endregion
    // endregion
    // endregion

    public static int valid(final int value, final int minValue)
    {
        if (value < minValue)
            throw new java.lang.IllegalArgumentException(
                java.lang.String.format("value must be >= %d", minValue));
        else
            return value;
    }

    // region Default SharedPreferences Methods
    private static android.content.SharedPreferences getDefaultSharedPreferences(
    final android.content.Context context)
    {
        if (null == org.wheatgenetics.coordinate.Utils.defaultSharedPreferencesInstance)
            org.wheatgenetics.coordinate.Utils.defaultSharedPreferencesInstance =
                android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return org.wheatgenetics.coordinate.Utils.defaultSharedPreferencesInstance;
    }

    public static org.wheatgenetics.coordinate.Utils.Advancement getAdvancement(
    final android.content.Context context)
    {
        assert null != context;
        final java.lang.String advancement =
            org.wheatgenetics.coordinate.Utils.getDefaultSharedPreferences(context).getString(
                /* key      => */"Advancement",
                /* defValue => */ context.getString(
                    org.wheatgenetics.coordinate.R.string.AdvancementPreferenceDefault));

        if (advancement.equals(context.getString(
        org.wheatgenetics.coordinate.R.string.AdvancementPreferenceDownThenAcrossEntryValue)))
            return org.wheatgenetics.coordinate.Utils.Advancement.DOWN_THEN_ACROSS;
        else
            if (advancement.equals(context.getString(
            org.wheatgenetics.coordinate.R.string.AdvancementPreferenceAcrossThenDownEntryValue)))
                return org.wheatgenetics.coordinate.Utils.Advancement.ACROSS_THEN_DOWN;
            else
                return org.wheatgenetics.coordinate.Utils.Advancement.ERROR;
    }

    static boolean getSoundOn(final android.content.Context context)
    {
        return org.wheatgenetics.coordinate.Utils.getDefaultSharedPreferences(context).getBoolean(
            "SoundOn", /* defValue => */true);
    }

    static org.wheatgenetics.coordinate.Utils.ProjectExport getProjectExport(
    final android.content.Context context)
    {
        assert null != context;
        final java.lang.String projectExport =
            org.wheatgenetics.coordinate.Utils.getDefaultSharedPreferences(context).getString(
                /* key      => */"ProjectExport",
                /* defValue => */ context.getString(
                    org.wheatgenetics.coordinate.R.string.ProjectExportPreferenceDefault));

        if (projectExport.equals(context.getString(
        org.wheatgenetics.coordinate.R.string.ProjectExportPreferenceOneFilePerGrid)))
            return org.wheatgenetics.coordinate.Utils.ProjectExport.ONE_FILE_PER_GRID;
        else
            if (projectExport.equals(context.getString(
            org.wheatgenetics.coordinate.R.string.ProjectExportPreferenceOneFileEntireProject)))
                return org.wheatgenetics.coordinate.Utils.ProjectExport.ONE_FILE_ENTIRE_PROJECT;
            else
                return org.wheatgenetics.coordinate.Utils.ProjectExport.ERROR;
    }
    // endregion

    public static java.lang.String convert(
    @android.support.annotation.IntRange(from = 0) final int offsetFromA)
    { return java.lang.Character.toString((char) ('A' + offsetFromA)); }
}