package com.example.EstatesFlow.Services.token;


import com.example.EstatesFlow.Entities.UserEntity.UserEntity;
import com.example.EstatesFlow.Entities.token.ConfirmationToken;
import org.jetbrains.annotations.NotNull;

public interface ConfirmationTokenService {


    public ConfirmationToken fetchTokenByToken(final String token);
    public String generateConfirmationToken(@NotNull UserEntity userEntity);
    public void setConfirmedAt(final String token);
    public void saveConfirmationToken(@NotNull ConfirmationToken confirmationToken);

    public String getConfirmationPage();
    public String getAlreadyConfirmedPage();

}
