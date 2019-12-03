package com.example.word;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonWord {

    /**
     * tSpeakUrl : http://openapi.youdao.com/ttsapi?q=%E8%8B%B9%E6%9E%9C&langType=zh-CHS&sign=1F3D05807498371169CE4F77441BF411&salt=1575275453601&voice=0&format=mp3&appKey=4eac84ad7ddbb253
     * returnPhrase : ["apple"]
     * web : [{"value":["苹果公司","苹果电脑","苹果汁"],"key":"Apple"},{"value":["苹果公司","美国苹果公司","苹果"],"key":"apple inc"},{"value":["大苹果","纽约","大苹果城","纽约的别称"],"key":"Big Apple"}]
     * query : apple
     * translation : ["苹果"]
     * errorCode : 0
     * dict : {"url":"yddict://m.youdao.com/dict?le=eng&q=apple"}
     * webdict : {"url":"http://m.youdao.com/dict?le=eng&q=apple"}
     * basic : {"exam_type":["初中"],"us-phonetic":"ˈæpl","phonetic":"ˈæpl","uk-phonetic":"ˈæpl","uk-speech":"http://openapi.youdao.com/ttsapi?q=apple&langType=en&sign=C17527A02A8FD4F94F15A8F098F4A5FC&salt=1575275453601&voice=5&format=mp3&appKey=4eac84ad7ddbb253","explains":["n. 苹果，苹果树，苹果似的东西；[美俚]炸弹，手榴弹，（棒球的）球；[美俚]人，家伙。"],"us-speech":"http://openapi.youdao.com/ttsapi?q=apple&langType=en&sign=C17527A02A8FD4F94F15A8F098F4A5FC&salt=1575275453601&voice=6&format=mp3&appKey=4eac84ad7ddbb253"}
     * l : en2zh-CHS
     * speakUrl : http://openapi.youdao.com/ttsapi?q=apple&langType=en&sign=C17527A02A8FD4F94F15A8F098F4A5FC&salt=1575275453601&voice=0&format=mp3&appKey=4eac84ad7ddbb253
     */

    private String tSpeakUrl;
    private String query;
    private String errorCode;
    private DictBean dict;
    private WebdictBean webdict;
    private BasicBean basic;
    private String l;
    private String speakUrl;
    private List<String> returnPhrase;
    private List<WebBean> web;
    private List<String> translation;

    public String getTSpeakUrl() {
        return tSpeakUrl;
    }

    public void setTSpeakUrl(String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DictBean getDict() {
        return dict;
    }

    public void setDict(DictBean dict) {
        this.dict = dict;
    }

    public WebdictBean getWebdict() {
        return webdict;
    }

    public void setWebdict(WebdictBean webdict) {
        this.webdict = webdict;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public List<String> getReturnPhrase() {
        return returnPhrase;
    }

    public void setReturnPhrase(List<String> returnPhrase) {
        this.returnPhrase = returnPhrase;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public static class DictBean {
        /**
         * url : yddict://m.youdao.com/dict?le=eng&q=apple
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class WebdictBean {
        /**
         * url : http://m.youdao.com/dict?le=eng&q=apple
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class BasicBean {
        /**
         * exam_type : ["初中"]
         * us-phonetic : ˈæpl
         * phonetic : ˈæpl
         * uk-phonetic : ˈæpl
         * uk-speech : http://openapi.youdao.com/ttsapi?q=apple&langType=en&sign=C17527A02A8FD4F94F15A8F098F4A5FC&salt=1575275453601&voice=5&format=mp3&appKey=4eac84ad7ddbb253
         * explains : ["n. 苹果，苹果树，苹果似的东西；[美俚]炸弹，手榴弹，（棒球的）球；[美俚]人，家伙。"]
         * us-speech : http://openapi.youdao.com/ttsapi?q=apple&langType=en&sign=C17527A02A8FD4F94F15A8F098F4A5FC&salt=1575275453601&voice=6&format=mp3&appKey=4eac84ad7ddbb253
         */

        @SerializedName("us-phonetic")
        private String usphonetic;
        private String phonetic;
        @SerializedName("uk-phonetic")
        private String ukphonetic;
        @SerializedName("uk-speech")
        private String ukspeech;
        @SerializedName("us-speech")
        private String usspeech;
        private List<String> exam_type;
        private List<String> explains;

        public String getUsphonetic() {
            return usphonetic;
        }

        public void setUsphonetic(String usphonetic) {
            this.usphonetic = usphonetic;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkphonetic() {
            return ukphonetic;
        }

        public void setUkphonetic(String ukphonetic) {
            this.ukphonetic = ukphonetic;
        }

        public String getUkspeech() {
            return ukspeech;
        }

        public void setUkspeech(String ukspeech) {
            this.ukspeech = ukspeech;
        }

        public String getUsspeech() {
            return usspeech;
        }

        public void setUsspeech(String usspeech) {
            this.usspeech = usspeech;
        }

        public List<String> getExam_type() {
            return exam_type;
        }

        public void setExam_type(List<String> exam_type) {
            this.exam_type = exam_type;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebBean {
        /**
         * value : ["苹果公司","苹果电脑","苹果汁"]
         * key : Apple
         */

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
}
