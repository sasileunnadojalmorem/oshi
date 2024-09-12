package com.oshi.ohsi_back.dto.response.Sale;

import java.math.BigDecimal;
import com.oshi.ohsi_back.entity.SaleEntity;
import com.oshi.ohsi_back.enums.SaleStatusEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaleResponseDto {

    private int salesId;
    private int writerId;
    private String writerName;
    private int oshiId;
    private String oshiName;
    private int categoryId;
    private String categoryName;
    private int goodsId;
    private String goodsName;
    private BigDecimal price; // 금액의 정밀도를 유지하기 위해 BigDecimal 사용
    private SaleStatusEnum status;
    private int imageId;
    private String description;
    private String imageUrl;

    public SaleResponseDto(SaleEntity saleEntity) {
        this.salesId = saleEntity.getSalesId();
        this.writerId = saleEntity.getUser().getUserId();
        this.writerName = saleEntity.getUser().getUsername();
        this.oshiId = saleEntity.getOshi().getOshiId();
        this.oshiName = saleEntity.getOshi().getName();
        this.categoryId = saleEntity.getCategory().getCategoryId();
        this.categoryName = saleEntity.getCategory().getName();
        this.goodsId = saleEntity.getGoods().getGoodsId();
        this.goodsName = saleEntity.getGoods().getName();
        this.price = saleEntity.getPrice(); // 그대로 BigDecimal로 사용
        this.status = saleEntity.getStatus();
        this.description = saleEntity.getDescription();

        // 이미지가 있는 경우에만 ID와 URL 설정
        this.imageId = saleEntity.getImage() == null ? 0 : saleEntity.getImage().getId();
        this.imageUrl = saleEntity.getImage() == null ? null : saleEntity.getImage().getUrl();
    }
    public SaleResponseDto(int salesId, int writerId, String writerName,
    int oshiId, String oshiName, int categoryId, String categoryName,
    int goodsId, String goodsName, BigDecimal price, String status,  // status는 String으로 받음
    int imageId, String description, String imageUrl) {
        this.salesId = salesId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.oshiId = oshiId;
        this.oshiName = oshiName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.status = SaleStatusEnum.valueOf(status);  // String을 Enum으로 변환
        this.imageId = imageId;
        this.description = description;
        this.imageUrl = imageUrl;
}
}