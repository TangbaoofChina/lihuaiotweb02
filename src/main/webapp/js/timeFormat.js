
//获取当前时间

function GetNowtime() {
    var date = new Date(); //日期对象
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';//取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + setFormat(date.getDate()) + " ";
    now = now + setFormat(date.getHours()) + ":";
    now = now + setFormat(date.getMinutes()) + ":";
    now = now + setFormat(date.getSeconds()) + "";
    return now;
}

//获取从现在到 beforetime 小时前的时间（beforetime 只能是整数）
function BeforeNowtime(beforetime) {
    var date = new Date(); //日期对象
    date.setHours(date.getHours() - beforetime);
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';//取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + setFormat(date.getDate()) + " ";
    now = now + setFormat(date.getHours()) + ":";
    now = now + setFormat(date.getMinutes()) + ":";
    now = now + setFormat(date.getSeconds()) + "";
    return now;
}

function NowWeeHours() {
    var date = new Date(); //日期对象
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';//取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + setFormat(date.getDate()) + " ";
    now = now + "00:00:00";
    return now;
}

function NowWeeMonth() {
    var date = new Date(); //日期对象
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';//取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + "01 ";
    now = now + "00:00:00";
    return now;
}

function NowWeeYear() {
    var date = new Date(); //日期对象
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + "01-";//取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + "01 ";
    now = now + "00:00:00";
    return now;
}

function YesterdayWeeHours() {
    var date = new Date(); //日期对象
    date.setDate(date.getDate() - 1);
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';//取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + setFormat(date.getDate()) + " ";
    now = now + "00:00:00";
    return now;
}

function YesterdayMidnight() {
    var date = new Date(); //日期对象
    date.setDate(date.getDate() - 1);
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';//取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + setFormat(date.getDate()) + " ";
    now = now + "23:59:59";
    return now;
}

//时间位数为1位数时，前面补0
var setFormat = function (x) {
    if (x < 10) {
        x = "0" + x;
    }
    return x;
}