import React from 'react'
import './style.css'
export default function Footer() {
  return (
    <div className='footer'>
        <div className='footer-container'>
            <div className="footer-top">
                <div className='footer-logo-box'>
                    <div className='icon-box'>
                        <div className='icon oshi-logo'></div>
                    </div>
                    <div className='footer-logo-text text'>{'Ohsi'}</div>    
                </div>

                <div className='footer-link-box'>
                    <div className="footer-email-link text">{'deao3558@gmail.com'}</div>
                    <div className="icon-button">
                        <div className='oshi-logo icon'></div>
                    </div>
                    <div className="icon-button">
                        <div className='eye-light-off-icon icon'></div>
                    </div>
                </div>
            </div>
            <div className="footer-bottom">
                <div className='copy-light text'>
                    {'copylight'}
                </div>
            </div>
        </div>
    </div>
  )
}
