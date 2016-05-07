package org.nazymko.controller.utils;

import org.springframework.ui.Model;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class MessagingUtils {
    public static class UI {
        public static void warn(Model model, String message) {
            model.addAttribute("warn", message);
        }

        public static void warn(Model model, String s, Object... params) {
            warn(model, String.format(s, params));
        }
    }
}
