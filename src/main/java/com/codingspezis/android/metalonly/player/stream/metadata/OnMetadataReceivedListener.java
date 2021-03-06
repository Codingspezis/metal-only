package com.codingspezis.android.metalonly.player.stream.metadata;

/**
 * Created by r on 17.09.14.
 */
public interface OnMetadataReceivedListener {

    /**
     * called whenever metadata gets available
     *
     * @param metadata matadata as string
     */
    public void onMetadataReceived(String metadata);

    /**
     * called whenever an error occurred during receiving metadata
     *
     * @param exception occurred exception
     */
    public void onMetadataError(Exception exception);

}
