package com.sdjeans.sdjeans_app.C_app.services;

import java.util.Optional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sdjeans.sdjeans_app.C_app.Dto.MemberDto;
import com.sdjeans.sdjeans_app.C_app.Entity.Member;
import com.sdjeans.sdjeans_app.C_app.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@MapperScan("com.sdjeans.sdjeans_app.C_app.mappers")
public class accountService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;



    @Autowired
    PasswordEncoder passwordEncoder; // ハッシュ化するやつ



    // login処理
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // Member member = userRepository.findById(id); // IDでユーザー検索
        Optional<Member> optionalMember = userRepository.findById(id);
        if (optionalMember.isEmpty()) {
            throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
        }

        Member member = optionalMember.get();
        return new UserPrincipal(member); // ユーザーが見つかった場合、UserPrincipalを作成して返す
    }

    // 新たにメソッドを追加します
    // public Member findByUsername(String id) {
    //     return userRepository.findById(id); // ユーザー名でユーザーを検索し返します
    // }

    public Member findByUsername(String id) {
        Optional<Member> optionalMember = userRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
        }

        return optionalMember.get(); // ユーザー名でユーザーを検索し返します
    }

    @Transactional
    public void save(MemberDto memberDto) {
        System.out.println("save");
        // MemberDtoからMemberへの変換
        Member member = new Member();
        member.setId(memberDto.getId());
        // パスワードをハッシュ化してから保存
        // member.setPw(passwordEncoder.encode(memberDto.getPw()));
        member.setPw(memberDto.getPw());
        // member.setAddress(memberDto.getAddress());

        // DBへ保存
        userRepository.save(member);
    }
}
