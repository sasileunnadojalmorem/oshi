import React from 'react';
import { pagenatedCategory } from 'types/interface';
import './categoryitem.css';
import defaultimg from 'assets/default image.jpg';
interface Props {
  categoryitem: pagenatedCategory;
}

export default function CategoryItem({ categoryitem }: Props) {
  const { Categorys } = categoryitem; // Categorys는 이제 배열입니다.

  return (
    <div>
      {Categorys.map((category, index) => (
        <div key={index} className="category-itembox">
          <div className="category-item-top-imagebox">
            <div
              className="category-item-top"
              style={{
                backgroundImage: `url(${category.categoryimg ? category.categoryimg : defaultimg})`,
              }}
            ></div>
          </div>
          <div className="category-item-middle">
            <div className="category-type">{category.categoryType}</div>
            <div className="category-name">{category.categoryName}</div>
          </div>
        </div>
      ))}
    </div>
  );
}
