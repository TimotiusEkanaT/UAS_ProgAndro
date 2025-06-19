package com.example.safevault_compose.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.safevault_compose.ui.screen.auth.*
import com.example.safevault_compose.ui.screen.calculator.Calc
import com.example.safevault_compose.ui.screen.note.Note
import com.example.safevault_compose.ui.screen.note.Notes_Detail
import com.example.safevault_compose.ui.screen.setting.Setting
import com.example.safevault_compose.ui.screen.setting.combination.*
import com.example.safevault_compose.ui.screen.setting.faceid.*
import com.example.safevault_compose.ui.screen.setting.fingerprint.*
import com.example.safevault_compose.ui.theme.SafeVault_ComposeTheme

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "auth_calc"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        // Auth screens
        composable("auth_calc") { Auth_Calc(navController) }
        composable("auth_register") { Auth_Register(navController) }
        composable("auth_combination") { Auth_Calc_Combination(navController) }
        composable("auth_biometric") {
            val context = LocalContext.current
            val activity = context as? FragmentActivity

            if (activity != null) {
                Auth_Calc_Biometric(navController, activity)
            } else {
                Text("Tidak dapat menampilkan autentikasi biometrik. Aktivitas tidak valid.")
            }
        }
        composable("auth_fingerprint") { Auth_Calc_FingerPrint(navController) }

        // Calculator
        composable("calculator") { Calc(navController) }

        // Vault - Notes
        composable("note") { Note(navController) }
        composable("note_detail?id={id}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("id")
            Notes_Detail(navController, noteId)
        }

        // Setting
        composable("setting") { Setting(navController) }

        // Setting - Combination
        composable("setting_combination") { Setting_Calc_Combination(navController) }
        composable("setting_combination_auth") { Setting_Calc_Combination_Auth(navController) }
        composable("setting_combination_submenu") { Setting_Calc_Combination_Submenu(navController) }

        // Setting - Face ID
        composable("setting_faceid") { Setting_FaceID(navController) }
        composable("setting_faceid_auth") { Setting_FaceID_Auth(navController) }
        composable("setting_faceid_submenu") { Setting_FaceID_Submenu(navController) }

        // Setting - Fingerprint
        composable("setting_fingerprint") { Setting_Fingerprint(navController) }
        composable("setting_fingerprint_auth") { Setting_Fingerprint_Auth(navController) }
        composable("setting_fingerprint_submenu") { Setting_Fingerprint_Submenu(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SafeVault_ComposeTheme {
        AppNavHost()
    }
}
