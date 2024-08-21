import React from 'react';
import './style.css';
import Dropdown from './dropdown'; // 새로 만든 Dropdown 컴포넌트를 import
import { Category, Goodsitem, Oshiitem ,Goodstypeitem} from 'types/interface';
import defaultimg from 'assets/default image.jpg'
interface Props {
  oshiitem: Oshiitem;
  categoryitem: Category;
  goodsitem: Goodsitem;
}

  export default function OshiListItem({ oshiitem, categoryitem, goodsitem }: Props) {
  const goodsTypes: Goodstypeitem = {
    goodstype: ['굿즈 종류 1', '굿즈 종류 2', '굿즈 종류 3'],
  }; 
  const {oshiName,oshiImage} = oshiitem;
  const {categoryName,categoryType} = categoryitem;
  const {goodsType,goodsName} = goodsitem;
  return (
    <div className='oshi-list-item'>
      <div className='oshi-list-main'>
        <div className='oshi-list-item-top'>
          <div className='oshi-list-item-logo'>
            <div className='oshi-list-item-imagebox'>
            <div
                className='oshi-list-item-image'
                style={{ backgroundImage: `url(${oshiImage ? oshiImage : defaultimg})` }} // } 문자 제거
              ></div>            </div>
            <div className='oshi-list-item-select'>
              <div className='oshi-list-item-selectimage' style={{ backgroundImage: 'url(/vector.png)' }}></div>
            </div>
          </div>
          <div className='oshi-list-item-who'>
            <div className='oshi-list-item-oshi'>{oshiName}</div>
            <div className='ohsi-list-item-category'>
              <div className='oshi-list-category-type'>{categoryType}</div>
              <div className='oshi-list-category-name'>{categoryName}</div>
            </div>
            <div className='oshi-list-item-goods'>
              <div className='oshi-list-goods-type'>{goodsType}</div>
              <div className='oshi-list-goods-name'>{goodsName}</div>
            </div>
          </div>
        </div>
        <div className='oshi-list-item-middle'>
          <div className='ohsi-list-item-search'>
            <div className='oshi-list-item-search-category'>{'카테고리별'}</div>
            <div className='oshi-list-item-search-goods'>{'굿즈별'}</div>
          </div>
        </div>
        <div className='oshi-list-item-bottom'>
          
          <div className='oshi-list-item-goods-sort'>
            <div className='oshi-list-item-goods-recent'>{'최신순'}</div>
            <div className='oshi-list-item-goods-newest'>{'발매순'}</div>
            <div className='oshi-list-item-goods-favorite'>{'인기순'}</div>
          </div>
        </div>
      </div>
    </div>
  );
}
