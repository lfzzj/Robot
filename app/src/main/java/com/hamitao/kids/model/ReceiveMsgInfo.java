package com.hamitao.kids.model;

import java.util.List;

/**
 * @data on 2018/4/10 18:08
 * @describe:
 */

public class ReceiveMsgInfo {

    /**
     * remoteControlCommands : {"contents":{"contentid":[""]},"action":"DEVICE_INFO","seqid":0}
     * source_channelid : 815c49344d82444d88b7bb16ae2ea38f
     * target_channelid : 06adb14afb6649389f613027dab75bb3
     */

    private RemoteControlCommandsBean remoteControlCommands;
    private String source_channelid;
    private String target_channelid;

    public RemoteControlCommandsBean getRemoteControlCommands() {
        return remoteControlCommands;
    }

    public void setRemoteControlCommands(RemoteControlCommandsBean remoteControlCommands) {
        this.remoteControlCommands = remoteControlCommands;
    }

    public String getSource_channelid() {
        return source_channelid;
    }

    public void setSource_channelid(String source_channelid) {
        this.source_channelid = source_channelid;
    }

    public String getTarget_channelid() {
        return target_channelid;
    }

    public void setTarget_channelid(String target_channelid) {
        this.target_channelid = target_channelid;
    }

    public static class RemoteControlCommandsBean {
        /**
         * contents : {"contentid":[""]}
         * action : DEVICE_INFO
         * seqid : 0
         */

        private ContentsBean contents;
        private String action;
        private int seqid;

        public ContentsBean getContents() {
            return contents;
        }

        public void setContents(ContentsBean contents) {
            this.contents = contents;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getSeqid() {
            return seqid;
        }

        public void setSeqid(int seqid) {
            this.seqid = seqid;
        }

        public static class ContentsBean {
            private List<String> contentid;

            public List<String> getContentid() {
                return contentid;
            }

            public void setContentid(List<String> contentid) {
                this.contentid = contentid;
            }
        }
    }
}
