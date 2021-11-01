<?php

defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * this library will be autoload
 *  
 * generating token for login user
 *
 * 
 *
 * @author    Amir Qushairi
 * @license   nerdtech
 */
class Generating_token {

	/**
	 * CodeIgniter instance
	 *
	 * @var object
	 */
	private $_ci;

	/**
	 * construct required library.
	 */
	function __construct() {
		// Get the CodeIgniter reference
		$this->_ci = &get_instance();
	}


	/**
	 * { function_description }
	 *
	 * @param      <type>  $token should be array
     * 
     * to use this function, just pass array as param
     * 
     * $this->generating_token->generate_token();
     * 
	 */
	public function generate_token($table_name) {

        $this->_ci->load->helper('string');

        $token_key = random_string('alnum', 8).random_string('basic', 8);

        // $bool = $this->exist_token($token_key, $table_name);
        
        for(;;)
        {
            if($this->exist_token($token_key, $table_name))
            {
                $token_key = random_string('alnum', 8).random_string('basic', 8);
            }
            else
            {
                return $token_key;
                break;
            }
        }        
        
	}
	
	public function generate_mobile_token($table_name) {
		$this->_ci->load->helper('string');

		$mobile_key = random_string('alnum', 8).random_string('basic', 8);

		for(;;)
        {
            $token_exist =  $this->_ci->db->where('mobile_token', $mobile_key)->get($table_name);
        
			if($token_exist->num_rows() > 0)
			{
				$mobile_key = random_string('alnum', 8).random_string('basic', 8);
			}
			else
			{
				return $mobile_key;
				break;
			}
        }  
		
	}

    public function update_user_token($table_name, $user_id, $data)
    {
        $this->_ci->db->where('id', $user_id);
        $this->_ci->db->update($table_name, $data);       

        if($this->_ci->db->affected_rows() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    
    public function exist_token($token_val, $token_table)
    {
        //check in db user already exist or not

        $token_exist =  $this->_ci->db->where('current_token', $token_val)->get($token_table);
        
        if($token_exist->num_rows() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }


    }
    
}
