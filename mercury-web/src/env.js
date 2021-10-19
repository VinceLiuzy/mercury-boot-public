(function (window) {
  window._env = window._env || {};

  window._env.server = 'http://127.0.0.1:8080/';

  // API url
  window._env.apiUrl = window._env.server + 'mercury-boot-api/api/';
  window._env.uploadUrl = window._env.server + 'mercury-boot-api/minio/upload';
  window._env.downloadUrl = window._env.server + 'mercury-boot-api/minio/download';

  // Whether or not to enable debug mode
  // Setting this to false will disable console output
  window._env.enableDebug = true;
}(this));
