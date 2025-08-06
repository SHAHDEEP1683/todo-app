import React, { useState } from 'react';
import axios from 'axios';

export default function SuperAdmin() {
  const [userId, setUserId] = useState('');
  const [message, setMessage] = useState('');

  const handleApprove = async () => {
    if (!userId.trim()) {
      alert("Please enter a User ID");
      return;
    }

    try {
      const response = await axios.post(`http://localhost:8080/api/admin/approve-admin/${userId}`);
      setMessage(response.data);
    } catch (error) {
      console.error("Approval failed:", error);
      setMessage("Error approving admin.");
    }
  };

  return (
    <div className="container mt-5" style={{ maxWidth: "500px" }}>
      <h3 className="mb-4 text-center">Approve Admin by ID</h3>

      <div className="mb-3">
        <label htmlFor="userId" className="form-label">User ID:</label>
        <input
          type="text"
          id="userId"
          className="form-control"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          placeholder="Enter user ID"
        />
      </div>

      <button className="btn btn-success w-100" onClick={handleApprove}>
        Approve Admin
      </button>

      {message && (
        <div className="alert alert-info mt-3" role="alert">
          {message}
        </div>
      )}
    </div>
  );
}
