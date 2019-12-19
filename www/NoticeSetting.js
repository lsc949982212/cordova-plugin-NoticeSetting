var exec = require('cordova/exec');

exports.systemNoticeSetting = function (success, error) {
    exec(success, error, 'NoticeSetting', 'systemNoticeSetting', []);
};
exports.securitySettings = function (success, error) {
    //exec(success, error, 'NoticeSetting', 'securitySettings', []);
};
