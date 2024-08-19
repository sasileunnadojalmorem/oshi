import React from 'react';
import './style.css';
import GoodsItemList from './GoodsItem/goodsitem'; // 단일 상품을 표시하는 컴포넌트
import GoodsPasing from './Pasing/goodsPasing'; // 페이지네이션 컴포넌트
import { pagenatedgoodsmock } from 'mocks';

export default function GoodsItemListPage() { // 컴포넌트 이름을 PascalCase로 변경
  return (
    <div className='goods-item-list'>
      <div className='product-grid'>
        {pagenatedgoodsmock.Goods.map((goods, index) => (
          <GoodsItemList
            key={index}
            goodsitem={{ Goods: [goods], Pageinfo: pagenatedgoodsmock.Pageinfo }}
          />
        ))}
      </div>
      <GoodsPasing pageitem={pagenatedgoodsmock} />
    </div>
  );
}
