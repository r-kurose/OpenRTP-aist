// -*-C++-*-
/*!
 * @file  module_ysuga_netSVC_impl.h
 * @brief Service implementation header of module_ysuga_net.idl
 *
 */

#include "module_ysuga_net_dataSkel.h"

#include "module_ysuga_netSkel.h"

#ifndef MODULE_YSUGA_NETSVC_IMPL_H
#define MODULE_YSUGA_NETSVC_IMPL_H
 
/*!
 * @class HogeSVC_impl
 * Example class implementing IDL interface ysuga_net::Hoge
 */
class ysuga_net_HogeSVC_impl
 : public virtual POA_ysuga_net::Hoge,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~HogeSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   HogeSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~ysuga_net_HogeSVC_impl();

   // attributes and operations
   ysuga_net::RETVAL* foo();

};



#endif // MODULE_YSUGA_NETSVC_IMPL_H


