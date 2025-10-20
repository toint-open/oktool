package cn.toint.oktool.util;

import cn.hutool.v7.core.text.StrUtil;

/**
 * @author Toint
 * @date 2025/9/10
 */
public class FileNameUtil extends cn.hutool.v7.core.io.file.FileNameUtil {
    /**
     * 重命名文件主名称(不会修改后缀)
     *
     * @param filePath        文件
     * @param newFileMainName 新的文件主名称(不含后缀)
     * @return 重命名后的文件名称
     */
    public static String renameMain(final String filePath, final String newFileMainName) {
        String fileName = getName(filePath);
        if (StrUtil.isBlank(fileName)) {
            return newFileMainName;
        }

        // 如果原始文件名称有后缀则保留
        final String suffix = getSuffix(fileName);
        if (StrUtil.isBlank(suffix)) {
            return newFileMainName;
        } else {
            return newFileMainName + "." + suffix;
        }
    }
}
