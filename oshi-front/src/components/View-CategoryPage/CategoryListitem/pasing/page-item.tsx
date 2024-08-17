import React from 'react';
import './style.css';
interface PageItemProps {
  pageNumber: number;
  isActive: boolean;
  onClick: () => void;
}

export default function PageItem({ pageNumber, isActive, onClick }: PageItemProps) {
  return (
    <div className={`page-item ${isActive ? 'active' : ''}`} onClick={onClick}>
      {pageNumber}
    </div>
  );
}
