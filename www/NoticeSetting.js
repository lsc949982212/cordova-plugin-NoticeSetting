var exec = require('cordova/exec');

exports.systemNoticeSetting = function (arg0, success, error) {
    exec(success, error, 'NoticeSetting', 'systemNoticeSetting', [arg0]);
};
