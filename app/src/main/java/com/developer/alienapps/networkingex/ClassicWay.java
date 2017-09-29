package com.developer.alienapps.networkingex;

public class ClassicWay {

    DownloadCompleteListener downloadCompleteListener;

    public ClassicWay(DownloadCompleteListener downloadCompleteListener) {
        this.downloadCompleteListener = downloadCompleteListener;
        DownloadRepoTask downloadRepoTask = new DownloadRepoTask(downloadCompleteListener);
        downloadRepoTask.execute("https://api.github.com/repositories");
    }
}
