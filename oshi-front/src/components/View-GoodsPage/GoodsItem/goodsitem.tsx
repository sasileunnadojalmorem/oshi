import React from 'react';
import { PagenatedGoods } from 'types/interface';
import './style.css';
import defaultimg from 'assets/default image.jpg';
interface Props {
  goodsitem: PagenatedGoods;
}

export default function GoodsItemList({ goodsitem }: Props) {
  const { Goods } = goodsitem; // Goods는 이제 배열입니다.

  return (
    <div>
      {Goods.map((goods, index) => (
        <div key={index} className="goods-itembox">
          <div className="goods-item-top-imagebox">
            <div
              className="goods-item-top"
              style={{
                backgroundImage: `url(${goods.goodsImage ? goods.goodsImage : defaultimg})`,
              }}
            ></div> 
          </div>
          <div className="goods-item-middle">
            <div className="goods-type">{goods.goodsType}</div>
            <div className="goods-name">{goods.goodsName}</div>
          </div>
        </div>
      ))}
    </div>
  );
}
