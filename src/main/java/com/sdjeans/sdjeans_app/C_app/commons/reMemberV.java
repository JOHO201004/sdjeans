// package com.sdjeans.sdjeans_app.C_app.commons;

// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;

// import com.sdjeans.sdjeans_app.C_app.forms.registerForm;

// public class reMemberV {

//     public BindingResult formCheck(registerForm form,BindingResult result,Model model){
//         if(!form.getPw().equals(form.getCfmPw())){
//             result.rejectValue("cfmPw", "password.mismatch", "パスワードと確認パスワードが一致しません");
//         }
//         if(form.getAddress().isEmpty()){
//             result.rejectValue("cfmAdd","address.isEmpty","住所を入力してください" );
//         }
//         if(form.getName().isEmpty()){
//             result.rejectValue("cfmName","Name.isEmpty","名前を入力してください" );
//         }
//         return result;

//     }
    
// }
