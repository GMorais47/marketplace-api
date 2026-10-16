package com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared;

public record ApiResponse<T>(
        boolean sucesso,
        String mensagem,
        T data
) {
    public static <T> ApiResponse<T> sucesso(String mensagem, T data){
        return new ApiResponse<>(true, mensagem, data);
    }

    public static <T> ApiResponse<T> error(String mensagem, T data){
        return new ApiResponse<>(false, mensagem, data);
    }
}
