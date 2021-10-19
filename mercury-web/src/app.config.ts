// Read environment variables from browser window
import {AppConfig} from 'zorro-lib';

const win = window || {};

/** 开发环境 */
export const APP_DEV_DI_CONFIG: AppConfig = {
  BASE_SERVICE: 'http://127.0.0.1:9800/mercury-boot-api/',
  FILE_UPLOAD: 'http://127.0.0.1:9800/mercury-boot-api/minio/upload',
  FILE_DOWNLOAD: 'http://127.0.0.1:9800/mercury-boot-api/minio/download',
  ENABLE_DEBUG: true,
};

/** 测试环境 */
export const APP_TEST_DI_CONFIG: AppConfig = {
  BASE_SERVICE: 'http://127.0.0.1:9800/mercury-boot-api/api/',
  FILE_UPLOAD: 'http://127.0.0.1:9800/mercury-boot-api/minio/upload',
  FILE_DOWNLOAD: 'http://127.0.0.1:9800/mercury-boot-api/minio/download',
  ENABLE_DEBUG: true,
};

/** 准生产环境 */
export const APP_STAGE_DI_CONFIG: AppConfig = {
  BASE_SERVICE: 'http://127.0.0.1:8080/mercury-boot-api/api/',
  FILE_UPLOAD: 'http://127.0.0.1:9800/mercury-boot-api/minio/upload',
  FILE_DOWNLOAD: 'http://127.0.0.1:9800/mercury-boot-api/minio/download',
  ENABLE_DEBUG: true,
};

/** 生产环境 */
export const APP_PROD_DI_CONFIG: AppConfig = {
  // @ts-ignore
  BASE_SERVICE: win[`_env`].apiUrl,
  // @ts-ignore
  FILE_UPLOAD: win[`_env`].uploadUrl,
  // @ts-ignore
  FILE_DOWNLOAD: win[`_env`].downloadUrl,
  // @ts-ignore
  ENABLE_DEBUG: win[`_env`].enableDebug,
};
