import React, { useRef } from 'react';
import './style.css';
import { oshimock } from 'mocks';
import OshiListView from 'components/OshiListView/oshilistview';

export default function View_Myoshi() {
  const containerRef = useRef<HTMLDivElement>(null);
  const isDragging = useRef(false);  // 드래그 상태를 추적

  const handleMouseDown = () => {
    isDragging.current = true;  // 드래그 시작
  };

  const handleMouseMove = (event: React.MouseEvent) => {
    if (!isDragging.current) return;  // 드래그 중이 아닐 때 무시

    const container = containerRef.current;
    if (container) {
      container.scrollLeft -= event.movementX;  // 마우스 이동에 따라 스크롤
    }
  };

  const handleMouseUp = () => {
    isDragging.current = false;  // 마우스 버튼을 놓으면 드래그 종료
  };

  const handleMouseLeave = () => {
    isDragging.current = false;  // 마우스가 컨테이너를 벗어나면 드래그 종료
  };

  return (
    <div>
        <div className="middle-box">
          <div
            className='oshi-container'
            ref={containerRef}
            onMouseDown={handleMouseDown}
            onMouseMove={handleMouseMove}
            onMouseUp={handleMouseUp}
            onMouseLeave={handleMouseLeave}
          >
            {oshimock.map((value,index) => (  
              <OshiListView
                key={index}
                Oshiinfo={{
                  oshiId: value.oshiId,
                  oshiImage: value.oshiImage,
                  oshiName: value.oshiName
                }}
              />
            ))}
          </div>
          {/* middleAddBox 컴포넌트 추가 */}
        </div>
      </div>
  );
}
