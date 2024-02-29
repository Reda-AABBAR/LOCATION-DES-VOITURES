import React from 'react'

function FeedBack() {
  return (<>
  <h1 style={{marginTop:'5%'}}>Share Your Rental Experience With Us</h1>
    <div style={{ height: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
      
      <div style={{ display: 'flex', justifyContent: 'space-around' }}>
        <div style={{margin:'10px', width: '250px', padding: '10px', border: '1px solid white', borderRadius: '5px', boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',alignItems: 'center',justifyContent: 'center',transition: 'box-shadow 0.3s ease-in-out'}}
         onMouseEnter={(e) => { e.target.style.boxShadow = '0 8px 16px rgba(0, 0, 0, 0.8)' }}
         onMouseLeave={(e) => { e.target.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.5)' }}
        >
          <img src='./Imag2/p1.PNG' alt="p1 msg" style={{width:'210px'}} />
          <br/>
          <p>I recently used the car rental app for a business trip, and overall, my experience was positive</p>
        </div>

        <div style={{margin:'10px', width: '250px', padding: '10px', border: '1px solid white', borderRadius: '5px', boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',alignItems: 'center',justifyContent: 'center'}}
         onMouseEnter={(e) => { e.target.style.boxShadow = '0 8px 16px rgba(0, 0, 0, 0.8)' }}
         onMouseLeave={(e) => { e.target.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.5)' }}
        >
          <img src='./Imag2/p2.PNG' alt="p1 msg" style={{width:'210px'}} />
          <br/>
          <p>the app met my expectations for car rental, and I would recommend it to other users due to its user-friendliness and efficiency in the rental process</p>
        </div>

        <div style={{margin:'10px', width: '250px', padding: '10px', border: '1px solid white', borderRadius: '5px', boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',alignItems: 'center',justifyContent: 'center'}}
         onMouseEnter={(e) => { e.target.style.boxShadow = '0 8px 16px rgba(0, 0, 0, 0.8)' }}
         onMouseLeave={(e) => { e.target.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.5)' }}
        >
          <img src='./Imag2/p5.PNG' alt="p1 msg" style={{width:'210px'}} />
          <br/>
          <p>Regarding cost transparency, I found the app provided clear information on rental fees, but it would be beneficial to include a detailed breakdown of possible additional costs</p>
        </div>

        <div style={{margin:'10px', width: '250px', padding: '10px', border: '1px solid white', borderRadius: '5px', boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',alignItems: 'center',justifyContent: 'center'}}
         onMouseEnter={(e) => { e.target.style.boxShadow = '0 8px 16px rgba(0, 0, 0, 0.8)' }}
         onMouseLeave={(e) => { e.target.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.5)' }}
        >
          <img src='./Imag2/p4.PNG' alt="p1 msg" style={{width:'210px'}} />
          <br/>
          <p>I recently used the car rental app for a business trip, and overall, my experience was positive</p>
        </div>

        <div style={{margin:'10px', width: '250px', padding: '10px', border: '1px solid white', borderRadius: '5px', boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',alignItems: 'center',justifyContent: 'center'}}
         onMouseEnter={(e) => { e.target.style.boxShadow = '0 8px 16px rgba(0, 0, 0, 0.8)' }}
         onMouseLeave={(e) => { e.target.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.5)' }}
        >
          <img src='./Imag2/p3.PNG' alt="p1 msg" style={{width:'210px'}} />
          <br/>
          <p>I recently used the car rental app for a business trip, and overall, my experience was positive</p>
        </div>

      </div>
    
    </div>
    </>
  )
}
export default  FeedBack;