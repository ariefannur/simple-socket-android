package client.websocket.arief.com.simplewebsocketclient.object;

import java.sql.Date;
import java.util.List;

/**
 * Created by arief pc on 6/4/2017.
 */

public class Comment {

    public class CommentResult {
        public List<Data> data;
        public int start_number;
        public int current_page;
        public int page_size;
        public int total_records;
    }

    public class CommentPost{
        public Data data;
        public String commentUID;
        public String comment;
        public String id;
    }

    public class Data{

        public String createdName;
        public int createdBy;
        public int fileSize;
        public String issueId;
        public String action;
        public String historyId;
        public String thumbnailUri;
        public String creatorAvatarImageUrl;
        public long writtenTime;
        public String unitName;
        public String unitGroupType;
        public String attachStatus;
        public String historyType;
        public String attachFileId;
        public String field;
        public String message;
        public String fileName;
    }
}
