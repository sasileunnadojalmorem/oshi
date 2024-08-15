import React from 'react';
import './style.css';
import CategoryItem from './categoryItem';
import { pagenatedmock } from 'mocks';
import CategoryPasing from './pasing/categoryPasing';
export default function CategoryItemList() {
  return (
    <div className='category-item-list'>
      <div className='product-grid'>
        {pagenatedmock.Categorys.map((category, index) => (
          <CategoryItem
            key={index}
            categoryitem={{ Categorys: [category], Pageinfo: pagenatedmock.Pageinfo }}
          />
        ))}
      </div>
      <CategoryPasing categoryitem={pagenatedmock}></CategoryPasing>

    </div>
  );
}
