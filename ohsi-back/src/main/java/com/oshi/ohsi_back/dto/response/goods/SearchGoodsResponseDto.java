    package com.oshi.ohsi_back.dto.response.goods;

    import org.springframework.data.domain.Page;
    import org.springframework.http.ResponseEntity;

    import com.oshi.ohsi_back.common.Responsecode;
    import com.oshi.ohsi_back.common.Responsemessage;
    import com.oshi.ohsi_back.dto.response.ResponseDto;
    import com.oshi.ohsi_back.entity.BaseGoodsEntity;

    import lombok.Builder;
    import lombok.Getter;
    import lombok.Setter;
    import java.util.List;
    @Getter
    @Setter
    public class SearchGoodsResponseDto   {
        

        private List<GoodsResponseDto> baseGoodsEntity;
        private int totalpages;
        private int totalCount;
        @Builder
        public SearchGoodsResponseDto(List<GoodsResponseDto> baseGoodsEntities, int totalPages, int totalCount) {
            this.baseGoodsEntity = baseGoodsEntities;
            this.totalpages = totalPages;
            this.totalCount = totalCount;
        }
    }